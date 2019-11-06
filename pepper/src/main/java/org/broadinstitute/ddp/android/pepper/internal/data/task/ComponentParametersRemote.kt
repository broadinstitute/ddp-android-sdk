package org.broadinstitute.ddp.android.pepper.internal.data.task

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.models.ComponentParameters
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class ComponentParametersRemote(
    @SerializedName("allowMultiple") val allowMultiple: Boolean?,
    @SerializedName("addButtonText") val addButtonText: String?,
    @SerializedName("titleText") val titleText: String?,
    @SerializedName("subtitleText") val subtitleText: String?,
    @SerializedName("institutionType") val institutionType: String?,
    @SerializedName("showFieldsInitially") val showFieldsInitially: Boolean?
) : Mappable<ComponentParameters> {

    override fun mapToResult(): Result<ComponentParameters> {
        val componentParameters = ComponentParameters(
            allowMultiple ?: false,
            addButtonText,
            titleText,
            subtitleText,
            institutionType,
            showFieldsInitially ?: false
        )

        return Result.Success(componentParameters)
    }
}