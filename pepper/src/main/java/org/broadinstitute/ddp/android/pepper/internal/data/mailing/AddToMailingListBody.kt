package org.broadinstitute.ddp.android.pepper.internal.data.mailing

import com.google.gson.annotations.SerializedName

internal data class AddToMailingListBody(
    @SerializedName("studyGuid") val studyId: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("emailAddress") val email: String
)