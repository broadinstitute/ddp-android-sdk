package org.broadinstitute.ddp.android.pepper.internal.data.user

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.Participant
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class ParticipantRemote(
    @SerializedName("userGuid") val id: String?,
    @SerializedName("alias") val alias: String?
) : Mappable<Participant> {
    override fun mapToResult(): Result<Participant> {
        if (id == null || alias == null) return Result.Failure(
            MalformedResponseException(this::class)
        )

        val participant = Participant(id, alias)

        return Result.Success(participant)
    }
}