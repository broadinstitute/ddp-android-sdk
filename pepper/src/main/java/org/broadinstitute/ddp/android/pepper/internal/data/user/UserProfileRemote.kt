package org.broadinstitute.ddp.android.pepper.internal.data.user

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.models.UserProfile
import org.broadinstitute.ddp.android.pepper.exposed.models.UserSex
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class UserProfileRemote(
    @SerializedName("birthMonth") val birthMonth: Int?,
    @SerializedName("birthYear") val birthYear: Int?,
    @SerializedName("birthDayInMonth") val birthDayInMonth: Int?,
    @SerializedName("sex") val sex: String?,
    @SerializedName("preferredLanguage") val preferredLanguage: String?,
    @SerializedName("firstName") val firstName: String?,
    @SerializedName("lastName") val lastName: String?
) : Mappable<UserProfile> {

    override fun mapToResult(): Result<UserProfile> {
        val user = UserProfile(
            birthMonth,
            birthYear,
            birthDayInMonth,
            UserSex.fromString(sex),
            preferredLanguage,
            firstName,
            lastName
        )

        return Result.Success(user)
    }
}