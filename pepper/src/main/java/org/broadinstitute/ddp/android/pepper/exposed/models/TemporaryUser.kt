package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TemporaryUser(
    val userId: String,
    val expiresAt: String
) : Parcelable