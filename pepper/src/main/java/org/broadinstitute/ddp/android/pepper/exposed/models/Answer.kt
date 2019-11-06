package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.GregorianCalendar
import java.util.Locale

sealed class Answer : Parcelable {

    abstract val questionId: String?
    abstract val answerId: String?
    abstract val value: Any?

    abstract fun updateQuestionId(questionId: String?): Answer

    abstract fun isValid(validations: List<Validation>): Boolean

    @Parcelize
    data class TextAnswer(
        override val questionId: String?,
        override val answerId: String?,
        override val value: String
    ) : Answer() {
        override fun updateQuestionId(questionId: String?): Answer =
            this.copy(questionId = questionId)

        override fun isValid(validations: List<Validation>): Boolean {
            return validations.all { validation ->
                when (validation) {
                    is Validation.Required -> value.isNotBlank()
                    else -> true
                }
            }
        }
    }

    @Parcelize
    data class BooleanAnswer(
        override val questionId: String?,
        override val answerId: String?,
        override val value: Boolean
    ) : Answer() {
        override fun updateQuestionId(questionId: String?): Answer =
            this.copy(questionId = questionId)

        override fun isValid(validations: List<Validation>): Boolean = true
    }

    @Parcelize
    data class DateAnswer(
        override val questionId: String?,
        override val answerId: String?,
        override val value: Date
    ) : Answer() {
        override fun updateQuestionId(questionId: String?): Answer =
            this.copy(questionId = questionId)

        override fun isValid(validations: List<Validation>): Boolean {
            val year = value.year
            val month = value.month
            val day = value.day

            return validations.all { validation ->
                when (validation) {
                    is Validation.Required -> year != null || month != null || day != null

                    is Validation.DateRange -> if (year != null && month != null && day != null) {
                        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        val timeInMillis = GregorianCalendar(year, month, day).timeInMillis
                        val startDateMillis = simpleDateFormat.parse(validation.startDate).time
                        val endDateMillis = simpleDateFormat.parse(validation.endDate).time
                        timeInMillis in startDateMillis..endDateMillis
                    } else {
                        true
                    }

                    else -> true
                }
            }
        }
    }

    @Parcelize
    data class PickListAnswer(
        override val questionId: String?,
        override val answerId: String?,
        override val value: List<PickedOption>
    ) : Answer() {
        override fun updateQuestionId(questionId: String?): Answer =
            this.copy(questionId = questionId)

        override fun isValid(validations: List<Validation>): Boolean {
            val size = value.size

            return validations.all { validation ->
                when (validation) {
                    is Validation.Required -> size > 0
                    else -> true
                }
            }
        }
    }

    @Parcelize
    data class CompositeAnswer(
        override val questionId: String?,
        override val answerId: String?,
        override val value: List<List<Answer>>
    ) : Answer() {
        override fun updateQuestionId(questionId: String?): Answer =
            this.copy(questionId = questionId)

        override fun isValid(validations: List<Validation>): Boolean {
            return value.firstOrNull()?.all { answer -> answer.isValid(validations) } ?: true
        }
    }

    @Parcelize
    data class UnknownAnswer(
        override val questionId: String?,
        override val answerId: String?,
        override val value: Unit?
    ) : Answer() {
        override fun updateQuestionId(questionId: String?): Answer =
            this.copy(questionId = questionId)

        override fun isValid(validations: List<Validation>): Boolean = true
    }
}