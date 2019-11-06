package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Section(
    val name: String?,
    val steps: List<Step>
) : Parcelable