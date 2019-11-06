package org.broadinstitute.ddp.android.pepper.internal.data.user

import com.google.gson.annotations.SerializedName

internal data class CreateParticipantBody(
    @SerializedName("alias") val alias: String
)