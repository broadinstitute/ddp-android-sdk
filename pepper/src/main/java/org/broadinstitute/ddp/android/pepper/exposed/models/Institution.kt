package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Institution(
    val name: String,
    val city: String,
    val state: String
) : Parcelable