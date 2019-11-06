package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DashboardTask(
    val instanceId: String,
    val taskName: String,
    val statusType: TaskStatusType?,
    val taskType: TaskType?,
    val readOnly: Boolean,
    val icon: String?
) : Parcelable