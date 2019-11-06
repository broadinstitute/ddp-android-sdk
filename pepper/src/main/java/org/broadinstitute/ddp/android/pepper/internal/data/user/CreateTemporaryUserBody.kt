package org.broadinstitute.ddp.android.pepper.internal.data.user

import com.google.gson.annotations.SerializedName

internal data class CreateTemporaryUserBody(
    @SerializedName("auth0ClientId") val auth0ClientId: String
)