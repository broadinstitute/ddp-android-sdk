package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Consent(
    val taskCode: String,
    val instanceId: String?,
    val consented: Boolean,
    val elections: List<Election>
) : Parcelable