package org.broadinstitute.ddp.android.pepper.internal.data.medicalprovider

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class CreateMedicalProviderResponse(
    @SerializedName("medicalProviderGuid") val medicalProviderId: String?
) : Mappable<String> {

    override fun mapToResult(): Result<String> {
        return if (medicalProviderId == null) return Result.Failure(MalformedResponseException(this::class))
        else Result.Success(medicalProviderId)
    }
}