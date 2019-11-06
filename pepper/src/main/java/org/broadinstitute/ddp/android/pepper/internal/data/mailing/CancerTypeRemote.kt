package org.broadinstitute.ddp.android.pepper.internal.data.mailing

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.CancerType
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class CancerTypeRemote(
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?
) : Mappable<CancerType> {

    override fun mapToResult(): Result<CancerType> {
        if (name == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val cancerType = CancerType(
            name,
            description
        )

        return Result.Success(cancerType)
    }
}