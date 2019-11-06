package org.broadinstitute.ddp.android.pepper.internal.data.user

import com.google.gson.annotations.SerializedName

internal data class UpdateEmailBody(
    @SerializedName("email") val email: String
)