package org.broadinstitute.ddp.android.pepper.exposed.models

enum class ValidationStatus(val constant: Int) {

    Invalid(0),
    Suggested(1),
    Valid(2);

    companion object {
        fun fromInt(constant: Int?): ValidationStatus? =
            values().find { value -> value.constant == constant }
    }
}