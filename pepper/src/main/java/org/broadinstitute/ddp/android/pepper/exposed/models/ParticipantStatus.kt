package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParticipantStatus(
    val medicalRecord: Record,
    val tissueRecord: Record,
    val kits: List<Kit>
) : Parcelable