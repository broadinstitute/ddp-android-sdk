package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StepVisibility(
    val stepId: String,
    val show: Boolean
) : Parcelable