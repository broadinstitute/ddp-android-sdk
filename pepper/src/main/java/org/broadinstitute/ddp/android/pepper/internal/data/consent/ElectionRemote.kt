package org.broadinstitute.ddp.android.pepper.internal.data.consent

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.models.Election
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class ElectionRemote(
    @SerializedName("stableId") val id: String?,
    @SerializedName("selected") val selected: Boolean?
) : Mappable<Election> {

    override fun mapToResult(): Result<Election> {
        val election = Election(id, selected ?: false)

        return Result.Success(election)
    }
}