package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Record(
    val status: TrackingStatus?,
    val requestedAt: Long?,
    val receivedBackAt: Long?
) : Parcelable
