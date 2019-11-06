package org.broadinstitute.ddp.android.pepper.internal.data.task

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.FormType
import org.broadinstitute.ddp.android.pepper.exposed.models.ListStyleHint
import org.broadinstitute.ddp.android.pepper.exposed.models.TaskInstance
import org.broadinstitute.ddp.android.pepper.exposed.models.TaskType
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class TaskInstanceRemote(
    @SerializedName("guid") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("activityType") val taskType: String?,
    @SerializedName("formType") val formType: String?,
    @SerializedName("listStyleHint") val listStyleHint: String?,
    @SerializedName("readonly") val readOnly: Boolean?,
    @SerializedName("sections") val sections: List<SectionRemote>?,
    @SerializedName("introduction") val introduction: SectionRemote?,
    @SerializedName("closing") val closing: SectionRemote?,
    @SerializedName("subtitle") val subTitle: String?,
    @SerializedName("lastUpdatedText") val lastUpdatedText: String?,
    @SerializedName("readonlyHint") val readOnlyHint: String?
) : Mappable<TaskInstance> {

    override fun mapToResult(): Result<TaskInstance> {
        if (id == null || name == null || status == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val sections = (sections ?: emptyList()).map { sectionRemote ->
            when (val mapResult = sectionRemote.mapToResult()) {
                is Result.Success -> mapResult.value
                is Result.Failure -> return Result.Failure(mapResult.error)
            }
        }

        val introduction = when (val introductionMapResult = introduction?.mapToResult()) {
            is Result.Success -> introductionMapResult.value
            is Result.Failure -> return Result.Failure(MalformedResponseException(this::class))
            else -> null
        }

        val closing = when (val closingMapResult = closing?.mapToResult()) {
            is Result.Success -> closingMapResult.value
            is Result.Failure -> return Result.Failure(MalformedResponseException(this::class))
            else -> null
        }

        val taskInstance = TaskInstance(
            id,
            name,
            status,
            TaskType.fromString(taskType),
            FormType.fromString(formType),
            ListStyleHint.fromString(listStyleHint),
            readOnly ?: false,
            sections,
            introduction,
            closing,
            subTitle,
            lastUpdatedText,
            readOnlyHint
        )

        return Result.Success(taskInstance)
    }
}