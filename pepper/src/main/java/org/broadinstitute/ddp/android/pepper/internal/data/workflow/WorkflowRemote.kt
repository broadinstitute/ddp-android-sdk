package org.broadinstitute.ddp.android.pepper.internal.data.workflow

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.models.Next
import org.broadinstitute.ddp.android.pepper.exposed.models.Workflow
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class WorkflowRemote(
    @SerializedName("next") val next: String?,
    @SerializedName("activityCode") val taskCode: String?,
    @SerializedName("instanceGuid") val instanceId: String?,
    @SerializedName("allowUnauthenticated") val allowUnauthenticated: Boolean?
) : Mappable<Workflow> {

    override fun mapToResult(): Result<Workflow> {
        val next = Next.fromString(next)
        val workflow = Workflow(next, taskCode, instanceId, allowUnauthenticated ?: false)
        return Result.Success(workflow)
    }
}