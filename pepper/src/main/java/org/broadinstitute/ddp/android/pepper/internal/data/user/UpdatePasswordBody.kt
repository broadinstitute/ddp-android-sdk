package org.broadinstitute.ddp.android.pepper.internal.data.user

import com.google.gson.annotations.SerializedName

internal data class UpdatePasswordBody(
    @SerializedName("currentPassword") val currentPassword: String,
    @SerializedName("password") val password: String
)