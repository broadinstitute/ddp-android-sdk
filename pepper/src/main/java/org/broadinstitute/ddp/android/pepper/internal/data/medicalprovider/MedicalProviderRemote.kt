package org.broadinstitute.ddp.android.pepper.internal.data.medicalprovider

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.MedicalProvider
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class MedicalProviderRemote(
    @SerializedName("medicalProviderGuid") val medicalProviderId: String?,
    @SerializedName("institutionName") val institutionName: String?,
    @SerializedName("physicianName") val physicianName: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("state") val state: String?
) : Mappable<MedicalProvider> {

    override fun mapToResult(): Result<MedicalProvider> {
        if (medicalProviderId == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val medicalProvider =
            MedicalProvider(medicalProviderId, institutionName, physicianName, city, state)

        return Result.Success(medicalProvider)
    }
}