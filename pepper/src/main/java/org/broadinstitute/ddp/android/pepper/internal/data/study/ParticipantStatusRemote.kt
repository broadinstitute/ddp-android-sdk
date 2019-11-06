package org.broadinstitute.ddp.android.pepper.internal.data.study

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.ParticipantStatus
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class ParticipantStatusRemote(
    @SerializedName("medicalRecords") val medicalRecord: RecordRemote?,
    @SerializedName("tissue") val tissueRecord: RecordRemote?,
    @SerializedName("kits") val kits: List<KitRemote>?
) : Mappable<ParticipantStatus> {

    override fun mapToResult(): Result<ParticipantStatus> {
        if (medicalRecord == null || tissueRecord == null || kits == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val medicalRecord = when (val mappingResult = medicalRecord.mapToResult()) {
            is Result.Success -> mappingResult.value
            is Result.Failure -> return Result.Failure(mappingResult.error)
        }

        val tissueRecord = when (val mappingResult = tissueRecord.mapToResult()) {
            is Result.Success -> mappingResult.value
            is Result.Failure -> return Result.Failure(mappingResult.error)
        }

        val kits = kits.map { kit ->
            when (val mappingResult = kit.mapToResult()) {
                is Result.Success -> mappingResult.value
                is Result.Failure -> return Result.Failure(mappingResult.error)
            }
        }

        val participantStatus = ParticipantStatus(
            medicalRecord,
            tissueRecord,
            kits
        )

        return Result.Success(participantStatus)
    }
}