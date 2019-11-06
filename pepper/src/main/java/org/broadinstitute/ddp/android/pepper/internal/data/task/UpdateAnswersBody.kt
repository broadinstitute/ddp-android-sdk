package org.broadinstitute.ddp.android.pepper.internal.data.task

import com.google.gson.annotations.SerializedName

internal data class UpdateAnswersBody(
    @SerializedName("answers") val answers: List<AnswerRemote>
)