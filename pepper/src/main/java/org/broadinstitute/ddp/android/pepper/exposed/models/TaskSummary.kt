package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TaskSummary(
    val instanceId: String,
    val taskCode: String,
    val readOnly: Boolean,
    val createdAt: Long,
    val taskName: String?,
    val taskDashboardName: String?,
    val taskSummary: String?,
    val taskSubtitle: String?,
    val taskType: TaskType?,
    val taskSubType: FormType?,
    val statusType: TaskStatusType?,
    val icon: String?,
    val numQuestions: Int,
    val numQuestionsAnswered: Int,
    val isFollowUp: Boolean
) : Parcelable {

    fun isQuestionsUnanswered() = numQuestionsAnswered < numQuestions
}