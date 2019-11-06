package org.broadinstitute.ddp.android.pepper.internal.data.consent

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.Consent
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class ConsentRemote(
    @SerializedName("activityCode") val taskCode: String?,
    @SerializedName("instanceGuid") val instanceId: String?,
    @SerializedName("consented") val consented: Boolean?,
    @SerializedName("elections") val elections: List<ElectionRemote>?
) : Mappable<Consent> {

    override fun mapToResult(): Result<Consent> {
        if (taskCode == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val elections = (elections ?: emptyList()).map { electionRemote ->
            when (val mapResult = electionRemote.mapToResult()) {
                is Result.Success -> mapResult.value
                is Result.Failure -> return Result.Failure(mapResult.error)
            }
        }

        val consent = Consent(taskCode, instanceId, consented ?: false, elections)

        return Result.Success(consent)
    }
}