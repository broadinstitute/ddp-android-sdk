package org.broadinstitute.ddp.android.pepper.exposed.models

import org.broadinstitute.ddp.android.pepper.internal.common.Constants

enum class Presentation(val constant: String) {

    Default(Constants.PRESENTATION_DEFAULT),
    Merge(Constants.PRESENTATION_MERGE);

    companion object {
        fun fromString(constant: String?): Presentation? =
            values().find { value -> value.constant == constant }
    }
}