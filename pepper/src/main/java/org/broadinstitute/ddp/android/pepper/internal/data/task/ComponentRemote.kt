package org.broadinstitute.ddp.android.pepper.internal.data.task

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.Component
import org.broadinstitute.ddp.android.pepper.internal.common.Constants
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class ComponentRemote(
    @SerializedName("componentType") val componentType: String?,
    @SerializedName("displayNumber") val displayNumber: Int?,
    @SerializedName("parameters") val parameters: ComponentParametersRemote?
) : Mappable<Component> {

    override fun mapToResult(): Result<Component> {
        if (componentType == null || parameters == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val parameters = when (val parametersMapResult = parameters.mapToResult()) {
            is Result.Success -> parametersMapResult.value
            is Result.Failure -> return Result.Failure(MalformedResponseException(this::class))
        }

        val component = when (componentType) {
            Constants.COMPONENT_TYPE_MAILING_ADDRESS -> Component.MailingAddressComponent(
                displayNumber,
                parameters
            )
            Constants.COMPONENT_TYPE_PHYSICIAN -> Component.InstitutionComponent(
                displayNumber,
                parameters
            )
            Constants.COMPONENT_TYPE_INSTITUTION -> Component.InstitutionComponent(
                displayNumber,
                parameters
            )
            else -> Component.UnknownComponent(displayNumber, parameters, componentType)
        }

        return Result.Success(component)
    }
}