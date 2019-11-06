package org.broadinstitute.ddp.android.pepper.internal.data.task

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.StepVisibility
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class StepVisibilityRemote(
    @SerializedName("blockGuid") val stepId: String?,
    @SerializedName("shown") val show: Boolean?
) : Mappable<StepVisibility> {

    override fun mapToResult(): Result<StepVisibility> {
        if (stepId == null) return Result.Failure(MalformedResponseException(this::class))

        val blockVisibility = StepVisibility(
            stepId,
            show ?: false
        )

        return Result.Success(blockVisibility)
    }
}