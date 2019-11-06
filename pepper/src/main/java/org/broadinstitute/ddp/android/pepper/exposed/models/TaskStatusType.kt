package org.broadinstitute.ddp.android.pepper.exposed.models

import org.broadinstitute.ddp.android.pepper.internal.common.Constants

enum class TaskStatusType(val constant: String) {

    Created(Constants.TASK_STATUS_CREATED),
    InProgress(Constants.TASK_STATUS_IN_PROGRESS),
    Complete(Constants.TASK_STATUS_COMPLETE);

    companion object {
        fun fromString(constant: String?): TaskStatusType? =
            values().find { value -> value.constant == constant }
    }
}