package org.broadinstitute.ddp.android.pepper.exposed.models

import org.broadinstitute.ddp.android.pepper.internal.common.Constants

enum class TextInputType(val constant: String) {

    SingleLine(Constants.TEXT_INPUT_TYPE_TEXT),
    MultiLine(Constants.TEXT_INPUT_TYPE_ESSAY),
    Signature(Constants.TEXT_INPUT_TYPE_SIGNATURE);

    companion object {
        fun fromString(constant: String?): TextInputType? =
            values().find { value -> value.constant == constant }
    }
}