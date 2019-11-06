package org.broadinstitute.ddp.android.pepper.exposed.models

import org.broadinstitute.ddp.android.pepper.internal.common.Constants

enum class ListStyleHint(val constant: String) {

    Number(Constants.LIST_STYLE_HINT_NUMBER),
    Bullet(Constants.LIST_STYLE_HINT_BULLET),
    None(Constants.LIST_STYLE_HINT_NONE);

    companion object {
        fun fromString(constant: String?): ListStyleHint? =
            values().find { value -> value.constant == constant }
    }
}