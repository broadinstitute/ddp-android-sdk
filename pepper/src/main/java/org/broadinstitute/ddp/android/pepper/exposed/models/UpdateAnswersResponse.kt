package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UpdateAnswersResponse(
    val answers: List<Answer>,
    val stepVisibilities: List<StepVisibility>
) : Parcelable