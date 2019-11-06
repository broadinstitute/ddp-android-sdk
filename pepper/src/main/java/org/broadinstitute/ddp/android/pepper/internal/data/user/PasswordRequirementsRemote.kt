package org.broadinstitute.ddp.android.pepper.internal.data.user

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.PasswordRequirements
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class PasswordRequirementsRemote(
    @SerializedName("minLength") val minLength: Int?,
    @SerializedName("isUppercaseLetterRequired") val isUppercaseLetterRequired: Boolean?,
    @SerializedName("isLowercaseLetterRequired") val isLowercaseLetterRequired: Boolean?,
    @SerializedName("isSpecialCharacterRequired") val isSpecialCharacterRequired: Boolean?,
    @SerializedName("isNumberRequired") val isNumberRequired: Boolean?,
    @SerializedName("maxIdenticalConsecutiveCharacters") val maxIdenticalConsecutiveCharacters: Int?
) : Mappable<PasswordRequirements> {

    override fun mapToResult(): Result<PasswordRequirements> {
        if (minLength == null || maxIdenticalConsecutiveCharacters == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val passwordRequirements = PasswordRequirements(
            minLength,
            isUppercaseLetterRequired ?: false,
            isLowercaseLetterRequired ?: false,
            isSpecialCharacterRequired ?: false,
            isNumberRequired ?: false,
            maxIdenticalConsecutiveCharacters
        )

        return Result.Success(passwordRequirements)
    }
}