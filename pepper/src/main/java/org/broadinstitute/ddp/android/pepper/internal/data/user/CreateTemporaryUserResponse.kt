package org.broadinstitute.ddp.android.pepper.internal.data.user

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.TemporaryUser
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class CreateTemporaryUserResponse(
    @SerializedName("userGuid") val userId: String?,
    @SerializedName("expiresAt") val expiresAt: String?
) : Mappable<TemporaryUser> {

    override fun mapToResult(): Result<TemporaryUser> {
        if (userId == null || expiresAt == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val temporaryUser = TemporaryUser(
            userId,
            expiresAt
        )

        return Result.Success(temporaryUser)
    }
}