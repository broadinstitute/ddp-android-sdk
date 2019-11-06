package org.broadinstitute.ddp.android.pepper.internal.data.task

import org.broadinstitute.ddp.android.pepper.exposed.PepperAuthenticator
import org.broadinstitute.ddp.android.pepper.exposed.models.Answer
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
import org.broadinstitute.ddp.android.pepper.internal.common.tryForResult
import org.broadinstitute.ddp.android.pepper.internal.data.PepperService
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal class TaskRemoteDataSource(
    private val pepperService: PepperService,
    private val pepperAuthenticator: PepperAuthenticator
) : TaskDataSource {

    override suspend fun getTaskSummaries(request: GetTaskSummariesRequest): Result<List<TaskSummary>> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId()
            val studyId = request.studyId

            pepperService
                .getTaskSummariesAsync(userId, studyId)
                .await()
                .mapNotNullTo(ArrayList()) { taskSummaryRemote ->
                    when (val mapResult = taskSummaryRemote.mapToResult()) {
                        is Result.Success -> mapResult.value
                        is Result.Failure -> null
                    }
                }.let { taskSummaries -> Result.Success(taskSummaries.toList()) }
        }

    override suspend fun getDashboardTasks(request: GetDashboardTasksRequest): Result<List<DashboardTask>> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId()
            val studyId = request.studyId

            pepperService
                .getDashboardTasksAsync(userId, studyId)
                .await()
                .mapNotNullTo(ArrayList()) { dashboardTaskRemote ->
                    when (val mapResult = dashboardTaskRemote.mapToResult()) {
                        is Result.Success -> mapResult.value
                        is Result.Failure -> null
                    }
                }.let { dashboardTasks -> Result.Success(dashboardTasks.toList()) }
        }

    override suspend fun getTaskInstance(request: GetTaskInstanceRequest): Result<TaskInstance> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId() ?: request.tempUserId
            val studyId = request.studyId
            val instanceId = request.instanceId

            pepperService
                .getTaskInstanceAsync(userId, studyId, instanceId)
                .await()
                .mapToResult()
        }

    override suspend fun updateAnswers(request: UpdateAnswersRequest): Result<UpdateAnswersResponse> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId() ?: request.tempUserId
            val studyId = request.studyId
            val instanceId = request.instanceId

            val remoteAnswers = request.answers.map { answer -> createAnswerRemote(answer) }
            val body = UpdateAnswersBody(remoteAnswers)

            pepperService.updateAnswersAsync(userId, studyId, instanceId, body)
                .await()
                .mapToResult()
        }

    override suspend fun completeTask(request: CompleteTaskRequest): Result<CompleteTaskResponse> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId() ?: request.tempUserId
            val studyId = request.studyId
            val instanceId = request.instanceId

            pepperService.completeTaskAsync(userId, studyId, instanceId)
                .await()
                .mapToResult()
        }

    private fun createAnswerRemote(answer: Answer): AnswerRemote {
        val value = when (answer) {
            is Answer.CompositeAnswer -> answer.value.map { answers ->
                answers.map { answer ->
                    createAnswerRemote(
                        answer
                    )
                }
            }
            is Answer.DateAnswer -> answer.value.let { date -> date.copy(month = date.month?.plus(1)) }
            else -> answer.value
        }

        return AnswerRemote(
            answer.questionId,
            answer.answerId,
            value,
            null
        )
    }
}