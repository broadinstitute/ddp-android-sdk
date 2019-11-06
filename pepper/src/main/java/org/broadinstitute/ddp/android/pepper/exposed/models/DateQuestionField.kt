package org.broadinstitute.ddp.android.pepper.exposed.models

import org.broadinstitute.ddp.android.pepper.internal.common.Constants

enum class DateQuestionField(val constant: String) {

    Year(Constants.DATE_QUESTION_FIELD_YEAR),
    Month(Constants.DATE_QUESTION_FIELD_MONTH),
    Day(Constants.DATE_QUESTION_FIELD_DAY);

    companion object {
        fun fromString(constant: String?): DateQuestionField? =
            values().find { value -> value.constant == constant }
    }
}