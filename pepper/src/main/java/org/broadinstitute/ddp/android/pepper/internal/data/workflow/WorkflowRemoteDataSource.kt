package org.broadinstitute.ddp.android.pepper.internal.data.workflow

import org.broadinstitute.ddp.android.pepper.exposed.PepperAuthenticator
import org.broadinstitute.ddp.android.pepper.exposed.models.Workflow
import org.broadinstitute.ddp.android.pepper.exposed.models.WorkflowState
import org.broadinstitute.ddp.android.pepper.exposed.requests.workflow.GetWorkflowRequest
import org.broadinstitute.ddp.android.pepper.internal.common.tryForResult
import org.broadinstitute.ddp.android.pepper.internal.data.PepperService
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal class WorkflowRemoteDataSource(
    private val pepperService: PepperService,
    private val pepperAuthenticator: PepperAuthenticator
) : WorkflowDataSource {

    override suspend fun getWorkflow(request: GetWorkflowRequest): Result<Workflow> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId() ?: request.tempUserId
            val studyId = request.studyId

            val getWorkflowCall = when (val state = request.state) {

                is WorkflowState.Task -> pepperService.getWorkflowAsync(
                    userId,
                    studyId,
                    state.value,
                    state.instanceId,
                    state.taskCode
                )
                is WorkflowState.ReturnUser,
                is WorkflowState.Start -> pepperService.getWorkflowAsync(
                    userId,
                    studyId,
                    state.value,
                    null,
                    null
                )
            }

            getWorkflowCall
                .await()
                .mapToResult()
        }
}