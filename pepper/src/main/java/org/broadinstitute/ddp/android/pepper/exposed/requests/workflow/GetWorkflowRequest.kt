package org.broadinstitute.ddp.android.pepper.exposed.requests.workflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.Workflow
import org.broadinstitute.ddp.android.pepper.exposed.models.WorkflowState
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class GetWorkflowRequest(val studyId: String, val state: WorkflowState, val tempUserId: String?) :
    PepperRequest<Workflow>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<Workflow>> =
        scope.async {
            dataSources.workflowDataSource.getWorkflow(this@GetWorkflowRequest)
        }
}