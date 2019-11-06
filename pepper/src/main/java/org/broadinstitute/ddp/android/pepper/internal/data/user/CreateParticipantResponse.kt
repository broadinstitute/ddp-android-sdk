package org.broadinstitute.ddp.android.pepper.internal.data.user

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class CreateParticipantResponse(
    @SerializedName("userGuid") val userId: String?
) : Mappable<String> {

    override fun mapToResult(): Result<String> {
        if (userId == null) return Result.Failure(MalformedResponseException(this::class))
        return Result.Success(userId)
    }
}