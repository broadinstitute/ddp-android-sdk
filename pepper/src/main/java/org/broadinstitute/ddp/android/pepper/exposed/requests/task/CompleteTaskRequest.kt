package org.broadinstitute.ddp.android.pepper.exposed.requests.task

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.CompleteTaskResponse
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class CompleteTaskRequest(
    val studyId: String,
    val instanceId: String,
    val tempUserId: String?
) : PepperRequest<CompleteTaskResponse>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<CompleteTaskResponse>> =
        scope.async {
            dataSources.taskDataSource.completeTask(this@CompleteTaskRequest)
        }
}