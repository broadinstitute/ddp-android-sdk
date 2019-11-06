package org.broadinstitute.ddp.android.pepper.exposed.models

import org.broadinstitute.ddp.android.pepper.internal.common.Constants

enum class FormType(val constant: String) {

    Consent(Constants.FORM_TYPE_CONSENT),
    General(Constants.FORM_TYPE_GENERAL),
    PreQualifier(Constants.FORM_TYPE_PREQUALIFIER);

    companion object {
        fun fromString(constant: String?): FormType? =
            values().find { value -> value.constant == constant }
    }
}