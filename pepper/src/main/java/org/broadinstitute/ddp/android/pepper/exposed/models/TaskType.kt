package org.broadinstitute.ddp.android.pepper.exposed.models

import org.broadinstitute.ddp.android.pepper.internal.common.Constants

enum class TaskType(val constant: String) {

    Forms(Constants.TASK_TYPE_FORMS);

    companion object {
        fun fromString(constant: String?): TaskType? =
            values().find { value -> value.constant == constant }
    }
}