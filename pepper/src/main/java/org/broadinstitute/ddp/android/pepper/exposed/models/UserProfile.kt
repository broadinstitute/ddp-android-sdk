package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserProfile(
    val birthMonth: Int?,
    val birthYear: Int?,
    val birthDayInMonth: Int?,
    val sex: UserSex?,
    val preferredLanguage: String?,
    val firstName: String?,
    val lastName: String?
) : Parcelable