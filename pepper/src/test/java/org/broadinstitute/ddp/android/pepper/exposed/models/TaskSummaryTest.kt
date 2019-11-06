package org.broadinstitute.ddp.android.pepper.exposed.models

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TaskSummaryTest {

    @Test
    fun isQuestionsUnansweredTrue() {
        val numQuestions = 10
        val numQuestionsAnswered = 5
        val taskSummary =
            getTaskSummary(numQuestions = numQuestions, numQuestionsAnswered = numQuestionsAnswered)

        val condition = taskSummary.isQuestionsUnanswered()
        assertTrue(condition)
    }

    @Test
    fun isQuestionsUnansweredFalse() {
        val numQuestions = 10
        val numQuestionsAnswered = 10
        val taskSummary =
            getTaskSummary(numQuestions = numQuestions, numQuestionsAnswered = numQuestionsAnswered)

        val condition = taskSummary.isQuestionsUnanswered()
        assertFalse(condition)
    }

    private fun getTaskSummary(
        instanceId: String = "instanceId",
        taskCode: String = "taskCode",
        readOnly: Boolean = false,
        createdAt: Long = 1000,
        taskName: String? = "taskName",
        taskDashboardName: String? = "taskDashboardName",
        taskSummary: String? = "taskSummary",
        taskSubtitle: String? = "taskSubtitle",
        taskType: TaskType? = TaskType.Forms,
        taskSubType: FormType? = FormType.General,
        statusType: TaskStatusType? = TaskStatusType.Created,
        icon: String? = "icon",
        numQuestions: Int = 10,
        numQuestionsAnswered: Int = 0,
        isFollowUp: Boolean = false
    ) = TaskSummary(
        instanceId,
        taskCode,
        readOnly,
        createdAt,
        taskName,
        taskDashboardName,
        taskSummary,
        taskSubtitle,
        taskType,
        taskSubType,
        statusType,
        icon,
        numQuestions,
        numQuestionsAnswered,
        isFollowUp
    )
}