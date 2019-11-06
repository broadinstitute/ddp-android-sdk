package org.broadinstitute.ddp.android.pepper.exposed.models

import org.broadinstitute.ddp.android.pepper.internal.common.Constants

enum class TrackingStatus(val constant: String) {

    Ineligible(Constants.TRACKING_STATUS_INELIGIBLE),
    Pending(Constants.TRACKING_STATUS_PENDING),
    Sent(Constants.TRACKING_STATUS_SENT),
    Received(Constants.TRACKING_STATUS_RECEIVED);

    companion object {
        fun fromString(constant: String?): TrackingStatus? =
            values().find { value -> value.constant == constant }
    }
}