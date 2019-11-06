package org.broadinstitute.ddp.android.pepper.internal.data.mailing

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.Country
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class CountryRemote(
    @SerializedName("name") val name: String?,
    @SerializedName("code") val code: String?
) : Mappable<Country> {

    override fun mapToResult(): Result<Country> {
        if (name == null || code == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val country = Country(name, code)
        return Result.Success(country)
    }
}