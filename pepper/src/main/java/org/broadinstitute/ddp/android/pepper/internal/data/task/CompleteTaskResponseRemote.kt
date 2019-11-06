package org.broadinstitute.ddp.android.pepper.internal.data.task

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.models.CompleteTaskResponse
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result
import org.broadinstitute.ddp.android.pepper.internal.data.workflow.WorkflowRemote

internal data class CompleteTaskResponseRemote(
    @SerializedName("workflow") val workflow: WorkflowRemote
) : Mappable<CompleteTaskResponse> {

    override fun mapToResult(): Result<CompleteTaskResponse> {
        val workflow = when (val workflowMapResult = workflow.mapToResult()) {
            is Result.Success -> workflowMapResult.value
            is Result.Failure -> return Result.Failure(workflowMapResult.error)
        }

        val completeTaskResponse = CompleteTaskResponse(
            workflow
        )

        return Result.Success(completeTaskResponse)
    }
}