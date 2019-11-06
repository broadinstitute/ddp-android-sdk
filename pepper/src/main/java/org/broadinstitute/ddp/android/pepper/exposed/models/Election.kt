package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Election(
    val id: String?,
    val selected: Boolean
) : Parcelable