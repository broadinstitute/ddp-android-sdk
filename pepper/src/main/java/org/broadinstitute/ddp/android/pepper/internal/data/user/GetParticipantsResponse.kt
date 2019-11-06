package org.broadinstitute.ddp.android.pepper.internal.data.user

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.Participant
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class GetParticipantsResponse(
    @SerializedName("participants") val participants: List<ParticipantRemote>?
) : Mappable<List<Participant>> {

    override fun mapToResult(): Result<List<Participant>> {
        if (participants == null) return Result.Failure(
            MalformedResponseException(this::class)
        )

        return participants.mapNotNullTo(ArrayList()) { participantRemote ->
            when (val mapResult = participantRemote.mapToResult()) {
                is Result.Success -> mapResult.value
                is Result.Failure -> null
            }
        }.let { participants ->
            Result.Success(participants)
        }
    }
}