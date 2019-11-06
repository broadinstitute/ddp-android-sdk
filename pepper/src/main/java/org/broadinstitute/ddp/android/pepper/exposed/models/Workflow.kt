package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Workflow(
    val next: Next,
    val taskCode: String?,
    val instanceId: String?,
    val allowUnauthenticated: Boolean
) : Parcelable