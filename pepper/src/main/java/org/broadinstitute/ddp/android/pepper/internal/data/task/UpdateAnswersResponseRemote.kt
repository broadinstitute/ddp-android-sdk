package org.broadinstitute.ddp.android.pepper.internal.data.task

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.UpdateAnswersResponse
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class UpdateAnswersResponseRemote(
    @SerializedName("answers") val answers: List<AnswerRemote>?,
    @SerializedName("blockVisibility") val stepVisibilities: List<StepVisibilityRemote>?
) : Mappable<UpdateAnswersResponse> {

    override fun mapToResult(): Result<UpdateAnswersResponse> {
        val answers = (answers ?: emptyList()).mapNotNullTo(ArrayList()) { answerRemote ->
            when (val mapResult = answerRemote.mapToResult()) {
                is Result.Success -> mapResult.value
                is Result.Failure -> null
            }
        }

        val blockVisibilities = (stepVisibilities ?: emptyList()).map { blockVisibilityRemote ->
            when (val mapResult = blockVisibilityRemote.mapToResult()) {
                is Result.Success -> mapResult.value
                is Result.Failure -> return Result.Failure(MalformedResponseException(this::class))
            }
        }

        val updateAnswersResponse = UpdateAnswersResponse(
            answers,
            blockVisibilities
        )

        return Result.Success(updateAnswersResponse)
    }
}