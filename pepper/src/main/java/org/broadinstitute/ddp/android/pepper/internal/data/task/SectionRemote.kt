package org.broadinstitute.ddp.android.pepper.internal.data.task

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.models.Section
import org.broadinstitute.ddp.android.pepper.exposed.models.Step
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class SectionRemote(
    @SerializedName("name") val name: String?,
    @SerializedName("blocks") val steps: List<StepRemote>?
) : Mappable<Section> {

    override fun mapToResult(): Result<Section> {
        val steps = (steps ?: emptyList()).fold(emptyList<Step>()) { steps, stepRemote ->
            val mapResult = stepRemote.mapToResult()
            steps + when (mapResult) {
                is Result.Success -> mapResult.value
                is Result.Failure -> return Result.Failure(mapResult.error)
            }
        }

        val section = Section(name, steps)

        return Result.Success(section)
    }
}