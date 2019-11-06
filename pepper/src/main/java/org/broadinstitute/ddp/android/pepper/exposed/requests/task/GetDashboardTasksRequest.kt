package org.broadinstitute.ddp.android.pepper.exposed.requests.task

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.DashboardTask
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class GetDashboardTasksRequest(val studyId: String) : PepperRequest<List<DashboardTask>>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<List<DashboardTask>>> =
        scope.async {
            dataSources.taskDataSource.getDashboardTasks(this@GetDashboardTasksRequest)
        }
}