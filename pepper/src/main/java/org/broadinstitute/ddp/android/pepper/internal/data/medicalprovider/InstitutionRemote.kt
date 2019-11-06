package org.broadinstitute.ddp.android.pepper.internal.data.medicalprovider

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.Institution
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class InstitutionRemote(
    @SerializedName("name") val name: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("state") val state: String?
) : Mappable<Institution> {

    override fun mapToResult(): Result<Institution> {
        if (name == null || city == null || state == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val institution = Institution(name, city, state)
        return Result.Success(institution)
    }
}