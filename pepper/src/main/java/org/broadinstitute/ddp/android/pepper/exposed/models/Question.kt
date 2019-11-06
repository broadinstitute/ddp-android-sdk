package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Question : Parcelable {

    abstract val id: String?
    abstract val type: String?
    abstract val prompt: String?
    abstract val textPrompt: String?
    abstract val additionalInfoHeader: String?
    abstract val additionalInfoFooter: String?
    abstract val answers: List<Answer>
    abstract val validations: List<Validation>

    fun isRequired(): Boolean = validations.any { validation -> validation is Validation.Required }

    fun isNotRequired(): Boolean = !isRequired()

    abstract fun updateAnswers(answers: List<Answer>): Question

    @Parcelize
    data class PickListQuestion(
        override val id: String,
        override val type: String,
        override val prompt: String?,
        override val textPrompt: String?,
        override val additionalInfoHeader: String?,
        override val additionalInfoFooter: String?,
        override val answers: List<Answer>,
        override val validations: List<Validation>,
        val selectMode: String,
        val renderMode: PickListRenderMode?,
        val label: String?,
        val options: List<PickListOption>
    ) : Question() {
        override fun updateAnswers(answers: List<Answer>): Question = this.copy(answers = answers)
    }

    @Parcelize
    data class TextQuestion(
        override val id: String,
        override val type: String,
        override val prompt: String?,
        override val textPrompt: String?,
        override val additionalInfoHeader: String?,
        override val additionalInfoFooter: String?,
        override val answers: List<Answer>,
        override val validations: List<Validation>,
        val inputType: TextInputType?,
        val placeholderText: String?,
        val suggestionType: SuggestionType,
        val suggestions: List<String>
    ) : Question() {
        override fun updateAnswers(answers: List<Answer>): Question = this.copy(answers = answers)
    }

    @Parcelize
    data class DrugTextQuestion(
        override val id: String,
        override val type: String,
        override val prompt: String?,
        override val textPrompt: String?,
        override val additionalInfoHeader: String?,
        override val additionalInfoFooter: String?,
        override val answers: List<Answer>,
        override val validations: List<Validation>,
        val child: TextQuestion,
        val allowMultiple: Boolean,
        val addButtonText: String?,
        val additionalItemText: String?
    ) : Question() {
        override fun updateAnswers(answers: List<Answer>): Question = this.copy(answers = answers)
    }

    @Parcelize
    data class BooleanQuestion(
        override val id: String,
        override val type: String,
        override val prompt: String?,
        override val textPrompt: String?,
        override val additionalInfoHeader: String?,
        override val additionalInfoFooter: String?,
        override val answers: List<Answer>,
        override val validations: List<Validation>,
        val trueContent: String,
        val falseContent: String
    ) : Question() {
        override fun updateAnswers(answers: List<Answer>): Question = this.copy(answers = answers)
    }

    @Parcelize
    data class DateQuestion(
        override val id: String,
        override val type: String,
        override val prompt: String?,
        override val textPrompt: String?,
        override val additionalInfoHeader: String?,
        override val additionalInfoFooter: String?,
        override val answers: List<Answer>,
        override val validations: List<Validation>,
        val renderMode: DateRenderMode?,
        val fields: List<DateQuestionField>,
        val displayCalendar: Boolean,
        val useMonthNames: Boolean,
        val startYear: Int?,
        val endYear: Int?,
        val firstSelectedYear: Int?
    ) : Question() {
        override fun updateAnswers(answers: List<Answer>): Question = this.copy(answers = answers)
    }

    @Parcelize
    data class CompositeQuestion(
        override val id: String,
        override val type: String,
        override val prompt: String?,
        override val textPrompt: String?,
        override val additionalInfoHeader: String?,
        override val additionalInfoFooter: String?,
        override val answers: List<Answer>,
        override val validations: List<Validation>,
        val children: List<Question>,
        val allowMultiple: Boolean,
        val addButtonText: String?,
        val additionalItemText: String?
    ) : Question() {
        override fun updateAnswers(answers: List<Answer>): Question = this.copy(answers = answers)
    }

    @Parcelize
    data class AgreementQuestion(
        override val id: String,
        override val type: String,
        override val prompt: String?,
        override val textPrompt: String?,
        override val additionalInfoHeader: String?,
        override val additionalInfoFooter: String?,
        override val answers: List<Answer>,
        override val validations: List<Validation>
    ) : Question() {
        override fun updateAnswers(answers: List<Answer>): Question = this.copy(answers = answers)
    }

    @Parcelize
    data class SignatureQuestion(
        override val id: String,
        override val type: String,
        override val prompt: String?,
        override val textPrompt: String?,
        override val additionalInfoHeader: String?,
        override val additionalInfoFooter: String?,
        override val answers: List<Answer>,
        override val validations: List<Validation>,
        val title: String?,
        val contentSteps: List<Step.ContentStep>,
        val textQuestion: TextQuestion,
        val dateQuestion: DateQuestion
    ) : Question() {
        override fun updateAnswers(answers: List<Answer>): Question = this.copy(answers = answers)
    }

    @Parcelize
    data class UnknownQuestion(
        override val id: String?,
        override val type: String?,
        override val prompt: String?,
        override val textPrompt: String?,
        override val additionalInfoHeader: String?,
        override val additionalInfoFooter: String?,
        override val answers: List<Answer>,
        override val validations: List<Validation>
    ) : Question() {
        override fun updateAnswers(answers: List<Answer>): Question = this.copy(answers = answers)
    }
}