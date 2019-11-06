package org.broadinstitute.ddp.android.pepper.internal.data.mailing

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.models.MailingAddress
import org.broadinstitute.ddp.android.pepper.exposed.models.ValidationStatus
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class MailingAddressRemote(
    @SerializedName("guid") val id: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("street1") val street1: String?,
    @SerializedName("street2") val street2: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("state") val state: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("zip") val zip: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("plusCode") val plusCode: String?,
    @SerializedName("isDefault") val isDefault: Boolean?,
    @SerializedName("validationStatus") val validationStatus: Int?
) : Mappable<MailingAddress> {

    override fun mapToResult(): Result<MailingAddress> {
        val validationStatus = ValidationStatus.fromInt(validationStatus)

        val mailingAddress = MailingAddress(
            id,
            description,
            name,
            street1,
            street2,
            city,
            state,
            country,
            zip,
            phone,
            plusCode,
            isDefault ?: false,
            validationStatus
        )

        return Result.Success(mailingAddress)
    }
}