package org.broadinstitute.ddp.android.pepper.exposed.models

import org.broadinstitute.ddp.android.pepper.internal.common.Constants

enum class PickListRenderMode(val constant: String) {

    SingleSelect(Constants.PICKLIST_RENDER_MODE_CHECKBOX_LIST),
    MultiSelect(Constants.PICKLIST_RENDER_MODE_LIST),
    DropDown(Constants.PICKLIST_RENDER_MODE_DROPDOWN);

    companion object {
        fun fromString(constant: String?): PickListRenderMode? =
            values().find { value -> value.constant == constant }
    }
}