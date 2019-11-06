package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Validation : Parcelable {

    abstract val rule: String
    abstract val message: String

    @Parcelize
    data class DateRange(
        override val rule: String,
        override val message: String,
        val startDate: String,
        val endDate: String
    ) : Validation()

    @Parcelize
    data class Required(override val rule: String, override val message: String) : Validation()

    @Parcelize
    data class Complete(override val rule: String, override val message: String) : Validation()

    @Parcelize
    data class AgeRange(
        override val rule: String,
        override val message: String,
        val minAge: Int?,
        val maxAge: Int?
    ) : Validation()
}