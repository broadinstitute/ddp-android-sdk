package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PickedOption(
    @SerializedName("stableId") val optionId: String,
    @SerializedName("detail") val detail: String?
) : Parcelable