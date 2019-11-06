package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MailingAddress(
    val id: String?,
    val description: String?,
    val name: String?,
    val street1: String?,
    val street2: String?,
    val city: String?,
    val state: String?,
    val country: String?,
    val zip: String?,
    val phone: String?,
    val plusCode: String?,
    val isDefault: Boolean,
    val validationStatus: ValidationStatus?
) : Parcelable