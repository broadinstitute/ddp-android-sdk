package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PickListOption(
    val id: String,
    val optionLabel: String?,
    val detailLabel: String?,
    val allowDetails: Boolean,
    val exclusive: Boolean
) : Parcelable