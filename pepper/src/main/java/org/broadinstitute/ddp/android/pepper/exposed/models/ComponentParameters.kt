package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComponentParameters(
    val allowMultiple: Boolean,
    val addButtonText: String?,
    val titleText: String?,
    val subtitleText: String?,
    val institutionType: String?,
    val showFieldsInitially: Boolean
) : Parcelable