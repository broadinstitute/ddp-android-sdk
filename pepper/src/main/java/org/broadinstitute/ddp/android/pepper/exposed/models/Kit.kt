package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Kit(
    val kitType: String?,
    val status: TrackingStatus?,
    val sentAt: Long?,
    val receivedBackAt: Long?,
    val trackingId: String?,
    val shipper: String?
) : Parcelable