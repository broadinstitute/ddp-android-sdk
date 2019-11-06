package org.broadinstitute.ddp.android.pepper.internal.data.task

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.DashboardTask
import org.broadinstitute.ddp.android.pepper.exposed.models.TaskStatusType
import org.broadinstitute.ddp.android.pepper.exposed.models.TaskType
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class DashboardTaskRemote(
    @SerializedName("instanceGuid") val instanceId: String?,
    @SerializedName("activityName") val taskName: String?,
    @SerializedName("statusCode") val statusCode: String?,
    @SerializedName("activityType") val taskType: String?,
    @SerializedName("readonly") val readOnly: Boolean?,
    @SerializedName("icon") val icon: String?
) : Mappable<DashboardTask> {

    override fun mapToResult(): Result<DashboardTask> {
        if (instanceId == null || taskName == null || statusCode == null || taskType == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val taskStatusType = TaskStatusType.fromString(statusCode)
        val taskType = TaskType.fromString(taskType)

        val dashboardTask = DashboardTask(
            instanceId,
            taskName,
            taskStatusType,
            taskType,
            readOnly ?: false,
            icon
        )

        return Result.Success(dashboardTask)
    }
}