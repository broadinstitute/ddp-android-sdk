package org.broadinstitute.ddp.android.pepper.internal.data.study

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.Study
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class StudyRemote(
    @SerializedName("studyGuid") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("participantCount") val participantCount: Int?,
    @SerializedName("registeredCount") val registeredCount: Int?,
    @SerializedName("restricted") val restricted: Boolean?,
    @SerializedName("summary") val summary: String?,
    @SerializedName("studyEmail") val studyEmail: String?
) : Mappable<Study> {

    override fun mapToResult(): Result<Study> {
        if (id == null || participantCount == null || registeredCount == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val study = Study(
            id,
            name,
            participantCount,
            registeredCount,
            restricted ?: false,
            summary,
            studyEmail
        )

        return Result.Success(study)
    }
}