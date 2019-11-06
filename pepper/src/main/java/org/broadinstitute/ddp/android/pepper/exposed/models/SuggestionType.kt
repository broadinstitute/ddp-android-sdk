package org.broadinstitute.ddp.android.pepper.exposed.models

import org.broadinstitute.ddp.android.pepper.internal.common.Constants

enum class SuggestionType(val constant: String) {

    None(Constants.SUGGESTION_TYPE_NONE),
    Drug(Constants.SUGGESTION_TYPE_DRUG),
    Included(Constants.SUGGESTION_TYPE_INCLUDED);

    companion object {
        fun fromString(constant: String?): SuggestionType =
            values().find { value -> value.constant == constant } ?: None
    }
}