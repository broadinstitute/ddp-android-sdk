package org.broadinstitute.ddp.android.pepper.internal.data.study

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.models.Record
import org.broadinstitute.ddp.android.pepper.exposed.models.TrackingStatus
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class RecordRemote(
    @SerializedName("status") val status: String?,
    @SerializedName("requestedAt") val requestedAt: Long?,
    @SerializedName("receivedBackAt") val receivedBackAt: Long?
) : Mappable<Record> {

    override fun mapToResult(): Result<Record> {

        val status = TrackingStatus.fromString(status)

        val record = Record(
            status,
            requestedAt,
            receivedBackAt
        )

        return Result.Success(record)
    }
}