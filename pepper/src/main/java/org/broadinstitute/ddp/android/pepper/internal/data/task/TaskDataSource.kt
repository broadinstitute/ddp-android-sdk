package org.broadinstitute.ddp.android.pepper.internal.data.task

import org.broadinstitute.ddp.android.pepper.exposed.models.CompleteTaskResponse
import org.broadinstitute.ddp.android.pepper.exposed.models.DashboardTask
import org.broadinstitute.ddp.android.pepper.exposed.models.TaskInstance
import org.broadinstitute.ddp.android.pepper.exposed.models.TaskSummary
import org.broadinstitute.ddp.android.pepper.exposed.models.UpdateAnswersResponse
import org.broadinstitute.ddp.android.pepper.exposed.requests.task.CompleteTaskRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.task.GetDashboardTasksRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.task.GetTaskInstanceRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.task.GetTaskSummariesRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.task.UpdateAnswersRequest
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal interface TaskDataSource {

    suspend fun getTaskSummaries(request: GetTaskSummariesRequest): Result<List<TaskSummary>>

    suspend fun getDashboardTasks(request: GetDashboardTasksRequest): Result<List<DashboardTask>>

    suspend fun getTaskInstance(request: GetTaskInstanceRequest): Result<TaskInstance>

    suspend fun updateAnswers(request: UpdateAnswersRequest): Result<UpdateAnswersResponse>

    suspend fun completeTask(request: CompleteTaskRequest): Result<CompleteTaskResponse>
}