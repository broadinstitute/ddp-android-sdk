package org.broadinstitute.ddp.android.pepper.internal.data.workflow

import org.broadinstitute.ddp.android.pepper.exposed.models.Workflow
import org.broadinstitute.ddp.android.pepper.exposed.requests.workflow.GetWorkflowRequest
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal interface WorkflowDataSource {

    suspend fun getWorkflow(request: GetWorkflowRequest): Result<Workflow>
}