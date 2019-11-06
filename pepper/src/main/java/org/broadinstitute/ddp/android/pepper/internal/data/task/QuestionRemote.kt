package org.broadinstitute.ddp.android.pepper.internal.data.task

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.Answer
import org.broadinstitute.ddp.android.pepper.exposed.models.DateQuestionField
import org.broadinstitute.ddp.android.pepper.exposed.models.DateRenderMode
import org.broadinstitute.ddp.android.pepper.exposed.models.PickListRenderMode
import org.broadinstitute.ddp.android.pepper.exposed.models.Question
import org.broadinstitute.ddp.android.pepper.exposed.models.SuggestionType
import org.broadinstitute.ddp.android.pepper.exposed.models.TextInputType
import org.broadinstitute.ddp.android.pepper.exposed.models.Validation
import org.broadinstitute.ddp.android.pepper.internal.common.Constants
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class QuestionRemote(
    @SerializedName("stableId") val id: String?,
    @SerializedName("questionType") val type: String?,
    @SerializedName("prompt") val prompt: String?,
    @SerializedName("textPrompt") val textPrompt: String?,
    @SerializedName("additionalInfoHeader") val additionalInfoHeader: String?,
    @SerializedName("additionalInfoFooter") val additionalInfoFooter: String?,
    @SerializedName("answers") val answers: List<AnswerRemote>?,
    @SerializedName("validations") val validations: List<ValidationRemote>?,
    @SerializedName("selectMode") val selectMode: String?,
    @SerializedName("renderMode") val renderMode: String?,
    @SerializedName("picklistLabel") val pickListLabel: String?,
    @SerializedName("picklistOptions") val pickListOptions: List<PickListOptionRemote>?,
    @SerializedName("inputType") val inputType: String?,
    @SerializedName("placeholderText") val placeholderText: String?,
    @SerializedName("trueContent") val trueContent: String?,
    @SerializedName("falseContent") val falseContent: String?,
    @SerializedName("fields") val fields: List<String>?,
    @SerializedName("displayCalendar") val displayCalendar: Boolean?,
    @SerializedName("useMonthNames") val useMonthNames: Boolean?,
    @SerializedName("startYear") val startYear: Int?,
    @SerializedName("endYear") val endYear: Int?,
    @SerializedName("firstSelectedYear") val firstSelectedYear: Int?,
    @SerializedName("children") val children: List<QuestionRemote>?,
    @SerializedName("allowMultiple") val allowMultiple: Boolean?,
    @SerializedName("addButtonText") val addButtonText: String?,
    @SerializedName("additionalItemText") val additionalItemText: String?,
    @SerializedName("suggestionType") val suggestionType: String?,
    @SerializedName("suggestions") val suggestions: List<String>?
) : Mappable<Question> {

    override fun mapToResult(): Result<Question> {
        if (id == null || answers == null || validations == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val answers = answers.map { answerRemote ->
            val answer = when (val mapResult = answerRemote.mapToResult()) {
                is Result.Success -> mapResult.value
                is Result.Failure -> return Result.Failure(mapResult.error)
            }

            if (answer.questionId == null) answer.updateQuestionId(id) else answer
        }

        val validations = validations.map { validationRemote ->
            when (val mapResult = validationRemote.mapToResult()) {
                is Result.Success -> mapResult.value
                is Result.Failure -> return Result.Failure(mapResult.error)
            }
        }

        return when (type) {
            Constants.QUESTION_TYPE_BOOLEAN -> createBooleanQuestion(id, type, answers, validations)
            Constants.QUESTION_TYPE_TEXT -> createTextQuestion(id, type, answers, validations)
            Constants.QUESTION_TYPE_PICK_LIST -> createPickListQuestion(
                id,
                type,
                answers,
                validations
            )
            Constants.QUESTION_TYPE_DATE -> createDateQuestion(id, type, answers, validations)
            Constants.QUESTION_TYPE_COMPOSITE -> createCompositeQuestion(
                id,
                type,
                answers,
                validations
            )
            Constants.QUESTION_TYPE_AGREEMENT -> createAgreementQuestion(
                id,
                type,
                answers,
                validations
            )
            else -> createUnknownQuestion()
        }
    }

    private fun String?.addAsteriskIfRequired(validations: List<Validation>): String? {
        if (this.isNullOrBlank()) return this
        val required = validations.any { validation -> validation is Validation.Required }
        return if (required) "$this *" else this
    }

    private fun createBooleanQuestion(
        id: String,
        type: String,
        answers: List<Answer>,
        validations: List<Validation>
    ): Result<Question> {
        if (trueContent == null || falseContent == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val question = Question.BooleanQuestion(
            id,
            type,
            prompt,
            textPrompt.addAsteriskIfRequired(validations),
            additionalInfoHeader,
            additionalInfoFooter,
            answers,
            validations,
            trueContent,
            falseContent
        )
        return Result.Success(question)
    }

    private fun createTextQuestion(
        id: String,
        type: String,
        answers: List<Answer>,
        validations: List<Validation>
    ): Result<Question> {
        val inputType = TextInputType.fromString(inputType)
        val suggestionType = SuggestionType.fromString(suggestionType)
        val suggestions = suggestions ?: emptyList()

        val question = Question.TextQuestion(
            id,
            type,
            prompt,
            textPrompt.addAsteriskIfRequired(validations),
            additionalInfoHeader,
            additionalInfoFooter,
            answers,
            validations,
            inputType,
            placeholderText,
            suggestionType,
            suggestions
        )

        return Result.Success(question)
    }

    private fun createPickListQuestion(
        id: String,
        type: String,
        answers: List<Answer>,
        validations: List<Validation>
    ): Result<Question> {
        if (selectMode == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val options = (pickListOptions ?: emptyList()).map { pickListOptionRemote ->
            when (val mapResult = pickListOptionRemote.mapToResult()) {
                is Result.Success -> mapResult.value
                is Result.Failure -> return Result.Failure(mapResult.error)
            }
        }

        val renderMode = PickListRenderMode.fromString(renderMode)

        val question = Question.PickListQuestion(
            id,
            type,
            prompt,
            textPrompt.addAsteriskIfRequired(validations),
            additionalInfoHeader,
            additionalInfoFooter,
            answers,
            validations,
            selectMode,
            renderMode,
            pickListLabel,
            options
        )

        return Result.Success(question)
    }

    private fun createDateQuestion(
        id: String,
        type: String,
        answers: List<Answer>,
        validations: List<Validation>
    ): Result<Question> {
        if (fields == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val renderMode = DateRenderMode.fromString(renderMode)

        val fields = fields.mapNotNull { field -> DateQuestionField.fromString(field) }

        val question = Question.DateQuestion(
            id,
            type,
            prompt,
            textPrompt.addAsteriskIfRequired(validations),
            additionalInfoHeader,
            additionalInfoFooter,
            answers,
            validations,
            renderMode,
            fields,
            displayCalendar ?: false,
            useMonthNames ?: false,
            startYear,
            endYear,
            firstSelectedYear
        )

        return Result.Success(question)
    }

    private fun createCompositeQuestion(
        id: String,
        type: String,
        answers: List<Answer>,
        validations: List<Validation>
    ): Result<Question> {
        val children = (children ?: emptyList()).map { questionRemote ->
            when (val mapResult = questionRemote.mapToResult()) {
                is Result.Success -> mapResult.value
                is Result.Failure -> return Result.Failure(mapResult.error)
            }
        }

        val firstChild = children.firstOrNull()

        val question = if (
            firstChild is Question.TextQuestion &&
            firstChild.suggestionType == SuggestionType.Drug &&
            children.size == 1
        ) {
            Question.DrugTextQuestion(
                id,
                type,
                prompt,
                textPrompt.addAsteriskIfRequired(validations),
                additionalInfoHeader,
                additionalInfoFooter,
                answers,
                validations,
                firstChild,
                allowMultiple ?: false,
                addButtonText,
                additionalItemText
            )
        } else {
            Question.CompositeQuestion(
                id,
                type,
                prompt,
                textPrompt.addAsteriskIfRequired(validations),
                additionalInfoHeader,
                additionalInfoFooter,
                answers,
                validations,
                children,
                allowMultiple ?: false,
                addButtonText,
                additionalItemText
            )
        }

        return Result.Success(question)
    }

    private fun createAgreementQuestion(
        id: String,
        type: String,
        answers: List<Answer>,
        validations: List<Validation>
    ): Result<Question> {
        val question = Question.AgreementQuestion(
            id,
            type,
            prompt,
            textPrompt.addAsteriskIfRequired(validations),
            additionalInfoHeader,
            additionalInfoFooter,
            answers,
            validations
        )

        return Result.Success(question)
    }

    private fun createUnknownQuestion(): Result<Question> {
        val question = Question.UnknownQuestion(
            id,
            type,
            prompt,
            textPrompt,
            additionalInfoHeader,
            additionalInfoFooter,
            emptyList(),
            emptyList()
        )

        return Result.Success(question)
    }
}