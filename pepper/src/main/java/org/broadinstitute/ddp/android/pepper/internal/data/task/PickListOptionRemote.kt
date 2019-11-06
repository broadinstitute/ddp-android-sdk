package org.broadinstitute.ddp.android.pepper.internal.data.task

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.PickListOption
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class PickListOptionRemote(
    @SerializedName("stableId") val id: String?,
    @SerializedName("optionLabel") val optionLabel: String?,
    @SerializedName("detailLabel") val detailLabel: String?,
    @SerializedName("allowDetails") val allowDetails: Boolean?,
    @SerializedName("exclusive") val exclusive: Boolean?
) : Mappable<PickListOption> {

    override fun mapToResult(): Result<PickListOption> {
        if (id == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val option =
            PickListOption(id, optionLabel, detailLabel, allowDetails ?: false, exclusive ?: false)

        return Result.Success(option)
    }
}