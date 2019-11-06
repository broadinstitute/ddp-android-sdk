package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Study(
    val id: String,
    val name: String?,
    val participantCount: Int,
    val registeredCount: Int,
    val restricted: Boolean,
    val summary: String?,
    val email: String?
) : Parcelable