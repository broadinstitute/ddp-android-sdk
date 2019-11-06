package org.broadinstitute.ddp.android.pepper.exposed.models

import org.broadinstitute.ddp.android.pepper.internal.common.Constants

enum class DateRenderMode(val constant: String) {

    Text(Constants.DATE_RENDER_MODE_TEXT),
    Picklist(Constants.DATE_RENDER_MODE_PICKLIST);

    companion object {
        fun fromString(constant: String?): DateRenderMode? =
            values().find { value -> value.constant == constant }
    }
}