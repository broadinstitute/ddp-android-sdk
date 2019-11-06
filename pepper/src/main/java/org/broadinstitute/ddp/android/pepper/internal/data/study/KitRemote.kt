package org.broadinstitute.ddp.android.pepper.internal.data.study

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.models.Kit
import org.broadinstitute.ddp.android.pepper.exposed.models.TrackingStatus
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class KitRemote(
    @SerializedName("kitType") val kitType: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("sentAt") val sentAt: Long?,
    @SerializedName("receivedBackAt") val receivedBackAt: Long?,
    @SerializedName("trackingId") val trackingId: String?,
    @SerializedName("shipper") val shipper: String?
) : Mappable<Kit> {

    override fun mapToResult(): Result<Kit> {
        val status = TrackingStatus.fromString(status)

        val kit = Kit(
            kitType,
            status,
            sentAt,
            receivedBackAt,
            trackingId,
            shipper
        )

        return Result.Success(kit)
    }
}