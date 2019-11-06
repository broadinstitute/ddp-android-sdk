package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PasswordRequirements(
    val minLength: Int,
    val isUppercaseLetterRequired: Boolean,
    val isLowercaseLetterRequired: Boolean,
    val isSpecialCharacterRequired: Boolean,
    val isNumberRequired: Boolean,
    val maxIdenticalConsecutiveCharacters: Int
) : Parcelable