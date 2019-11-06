package org.broadinstitute.ddp.android.pepper.exposed.requests.task

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.TaskSummary
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class GetTaskSummariesRequest(val studyId: String) : PepperRequest<List<TaskSummary>>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<List<TaskSummary>>> =
        scope.async {
            dataSources.taskDataSource.getTaskSummaries(this@GetTaskSummariesRequest)
        }
}