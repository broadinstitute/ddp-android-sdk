package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    val name: String,
    val code: String
) : Parcelable