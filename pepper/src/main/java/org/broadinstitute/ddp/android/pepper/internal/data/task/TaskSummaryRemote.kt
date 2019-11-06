package org.broadinstitute.ddp.android.pepper.internal.data.task

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.FormType
import org.broadinstitute.ddp.android.pepper.exposed.models.TaskStatusType
import org.broadinstitute.ddp.android.pepper.exposed.models.TaskSummary
import org.broadinstitute.ddp.android.pepper.exposed.models.TaskType
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class TaskSummaryRemote(
    @SerializedName("instanceGuid") val instanceId: String?,
    @SerializedName("activityCode") val taskCode: String?,
    @SerializedName("readonly") val readOnly: Boolean?,
    @SerializedName("createdAt") val createdAt: Long?,
    @SerializedName("activityName") val taskName: String?,
    @SerializedName("activityDashboardName") val taskDashboardName: String?,
    @SerializedName("activitySummary") val taskSummary: String?,
    @SerializedName("activitySubtitle") val taskSubtitle: String?,
    @SerializedName("activityType") val taskType: String?,
    @SerializedName("activitySubtype") val taskSubType: String?,
    @SerializedName("statusCode") val statusCode: String?,
    @SerializedName("icon") val icon: String?,
    @SerializedName("numQuestions") val numQuestions: Int?,
    @SerializedName("numQuestionsAnswered") val numQuestionsAnswered: Int?,
    @SerializedName("isFollowup") val isFollowUp: Boolean?
) : Mappable<TaskSummary> {

    override fun mapToResult(): Result<TaskSummary> {
        if (instanceId == null ||
            taskCode == null ||
            createdAt == null ||
            numQuestions == null ||
            numQuestionsAnswered == null
        ) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val taskSummary = TaskSummary(
            instanceId,
            taskCode,
            readOnly ?: false,
            createdAt,
            taskName,
            taskDashboardName,
            taskSummary,
            taskSubtitle,
            TaskType.fromString(taskType),
            FormType.fromString(taskSubType),
            TaskStatusType.fromString(statusCode),
            icon,
            numQuestions,
            numQuestionsAnswered,
            isFollowUp ?: false
        )

        return Result.Success(taskSummary)
    }
}