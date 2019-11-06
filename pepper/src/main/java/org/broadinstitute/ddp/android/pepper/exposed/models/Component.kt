package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Component : Parcelable {

    abstract val displayNumber: Int?
    abstract val parameters: ComponentParameters

    @Parcelize
    data class MailingAddressComponent(
        override val displayNumber: Int?,
        override val parameters: ComponentParameters
    ) : Component()

    @Parcelize
    data class InstitutionComponent(
        override val displayNumber: Int?,
        override val parameters: ComponentParameters
    ) : Component()

    @Parcelize
    data class UnknownComponent(
        override val displayNumber: Int?,
        override val parameters: ComponentParameters,
        val componentType: String?
    ) : Component()
}