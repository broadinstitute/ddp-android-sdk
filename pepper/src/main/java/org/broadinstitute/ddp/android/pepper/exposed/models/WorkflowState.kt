package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.broadinstitute.ddp.android.pepper.internal.common.Constants

sealed class WorkflowState : Parcelable {

    abstract val value: String

    @Parcelize
    data class Task(
        val instanceId: String?,
        val taskCode: String?,
        override val value: String = Constants.WORKFLOW_STATE_TASK
    ) : WorkflowState()

    @Parcelize
    data class ReturnUser(override val value: String = Constants.WORKFLOW_STATE_RETURN_USER) :
        WorkflowState()

    @Parcelize
    data class Start(override val value: String = Constants.WORKFLOW_STATE_START) : WorkflowState()
}