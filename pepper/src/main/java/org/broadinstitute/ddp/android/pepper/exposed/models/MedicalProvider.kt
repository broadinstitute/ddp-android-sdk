package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MedicalProvider(
    val medicalProviderId: String,
    val institutionName: String?,
    val physicianName: String?,
    val city: String?,
    val state: String?
) : Parcelable