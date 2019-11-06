package org.broadinstitute.ddp.android.pepper.internal.data.task

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.Presentation
import org.broadinstitute.ddp.android.pepper.exposed.models.Question
import org.broadinstitute.ddp.android.pepper.exposed.models.Step
import org.broadinstitute.ddp.android.pepper.exposed.models.TextInputType
import org.broadinstitute.ddp.android.pepper.internal.common.Constants
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class StepRemote(
    @SerializedName("blockGuid") val id: String?,
    @SerializedName("blockType") val type: String?,
    @SerializedName("shown") val show: Boolean?,
    @SerializedName("title") val title: String?,
    @SerializedName("body") val body: String?,
    @SerializedName("question") val question: QuestionRemote?,
    @SerializedName("nested") val nested: List<StepRemote>?,
    @SerializedName("control") val control: QuestionRemote?,
    @SerializedName("listStyleHint") val listStyleHint: String?,
    @SerializedName("component") val component: ComponentRemote?,
    @SerializedName("presentation") val presentation: String?
) : Mappable<List<Step>> {

    override fun mapToResult(): Result<List<Step>> {
        if (id == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val show = show ?: false

        return when (type) {
            Constants.STEP_TYPE_COMPONENT -> createComponentStep(id, type, show)
            Constants.STEP_TYPE_CONDITIONAL -> createConditionalStep(id, type, show)
            Constants.STEP_TYPE_GROUP -> createGroupStep(id, type, show)
            Constants.STEP_TYPE_CONTENT -> createContentStep(id, type, show)
            Constants.STEP_TYPE_QUESTION -> createQuestionStep(id, type, show)
            else -> createUnknownStep()
        }
    }

    private fun createComponentStep(id: String, type: String, show: Boolean): Result<List<Step>> {
        if (component == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val component = when (val componentMapResult = component.mapToResult()) {
            is Result.Success -> componentMapResult.value
            is Result.Failure -> return Result.Failure(MalformedResponseException(this::class))
        }

        val step = Step.ComponentStep(id, type, show, component)
        return Result.Success(listOf(step))
    }

    private fun createConditionalStep(id: String, type: String, show: Boolean): Result<List<Step>> {
        if (control == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val controlQuestion = when (val controlMapResult = control.mapToResult()) {
            is Result.Success -> controlMapResult.value
            is Result.Failure -> return Result.Failure(MalformedResponseException(this::class))
        }

        val nestedSteps = (nested ?: emptyList()).fold(emptyList<Step>()) { steps, stepRemote ->
            val mapResult = stepRemote.mapToResult()
            steps + when (mapResult) {
                is Result.Success -> mapResult.value
                is Result.Failure -> return Result.Failure(mapResult.error)
            }
        }

        val controlStep = Step.QuestionStep(id, type, show, controlQuestion)
        return Result.Success(listOf(controlStep) + nestedSteps)
    }

    private fun createGroupStep(id: String, type: String, show: Boolean): Result<List<Step>> {
        val nestedSteps = (nested ?: emptyList()).fold(emptyList<Step>()) { steps, stepRemote ->
            val mapResult = stepRemote.mapToResult()
            steps + when (mapResult) {
                is Result.Success -> mapResult.value
                is Result.Failure -> return Result.Failure(mapResult.error)
            }
        }

        val presentation = Presentation.fromString(presentation)

        val allContentStep = nestedSteps.isNotEmpty() &&
            nestedSteps.all { step -> step is Step.ContentStep }

        val signatureQuestions = nestedSteps.filterIsInstance<Step.QuestionStep>()
            .map { step -> step.question }
            .filterIsInstance<Question.CompositeQuestion>()
            .flatMap { question -> question.children }

        val signatureStep = nestedSteps.isNotEmpty() &&
            presentation == Presentation.Merge &&
            signatureQuestions.filterIsInstance<Question.TextQuestion>()
                .any { question -> question.inputType == TextInputType.Signature } &&
            signatureQuestions.filterIsInstance<Question.DateQuestion>()
                .isNotEmpty()

        val steps = when {
            allContentStep -> {
                val contentSteps = nestedSteps.filterIsInstance<Step.ContentStep>()
                listOf(Step.GroupedContentStep(id, type, show, contentSteps, title))
            }
            signatureStep -> {
                val contentSteps = nestedSteps.filterIsInstance<Step.ContentStep>()

                val compositeQuestion = nestedSteps.filterIsInstance<Step.QuestionStep>()
                    .map { step -> step.question }
                    .filterIsInstance<Question.CompositeQuestion>()
                    .first()

                val textQuestion =
                    compositeQuestion.children.filterIsInstance<Question.TextQuestion>().first()
                val dateQuestion =
                    compositeQuestion.children.filterIsInstance<Question.DateQuestion>().first()

                val question = Question.SignatureQuestion(
                    compositeQuestion.id,
                    compositeQuestion.type,
                    compositeQuestion.prompt,
                    compositeQuestion.textPrompt,
                    compositeQuestion.additionalInfoHeader,
                    compositeQuestion.additionalInfoFooter,
                    compositeQuestion.answers,
                    compositeQuestion.validations,
                    title,
                    contentSteps,
                    textQuestion,
                    dateQuestion
                )

                listOf(Step.QuestionStep(id, type, show, question))
            }
            else -> nestedSteps
        }

        return Result.Success(steps)
    }

    private fun createContentStep(id: String, type: String, show: Boolean): Result<List<Step>> {
        val step = Step.ContentStep(id, type, show, title, body)
        return Result.Success(listOf(step))
    }

    private fun createQuestionStep(id: String, type: String, show: Boolean): Result<List<Step>> {
        val step = when (val mapResult = question?.mapToResult()) {
            is Result.Success -> Step.QuestionStep(id, type, show, mapResult.value)
            is Result.Failure -> return Result.Failure(mapResult.error)
            else -> return Result.Failure(MalformedResponseException(this::class))
        }

        return Result.Success(listOf(step))
    }

    private fun createUnknownStep(): Result<List<Step>> {
        val step = Step.UnknownStep(id, type, false)
        return Result.Success(listOf(step))
    }
}