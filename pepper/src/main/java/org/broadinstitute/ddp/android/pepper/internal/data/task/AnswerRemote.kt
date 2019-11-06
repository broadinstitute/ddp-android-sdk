package org.broadinstitute.ddp.android.pepper.internal.data.task

import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.Answer
import org.broadinstitute.ddp.android.pepper.exposed.models.Date
import org.broadinstitute.ddp.android.pepper.exposed.models.PickedOption
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class AnswerRemote(
    @SerializedName("stableId") val questionId: String?,
    @SerializedName("answerGuid") val answerId: String?,
    @SerializedName("value") val value: Any?,
    @SerializedName("type") val type: String?
) : Mappable<Answer> {

    override fun mapToResult(): Result<Answer> {
        return when {
            type == TEXT && value is String -> createTextAnswer(questionId, answerId, value)
            type == BOOLEAN && value is Boolean -> createBooleanAnswer(questionId, answerId, value)
            type == DATE && value is LinkedTreeMap<*, *> -> createDateAnswer(
                questionId,
                answerId,
                value
            )
            type == PICKLIST && value is ArrayList<*> -> createPickListAnswer(
                questionId,
                answerId,
                value
            )
            type == COMPOSITE && value is ArrayList<*> -> createCompositeAnswer(
                questionId,
                answerId,
                value
            )
            else -> createUnknownAnswer()
        }
    }

    private fun createTextAnswer(
        questionId: String?,
        answerId: String?,
        value: String
    ): Result<Answer> {
        val answer = Answer.TextAnswer(questionId, answerId, value)
        return Result.Success(answer)
    }

    private fun createBooleanAnswer(
        questionId: String?,
        answerId: String?,
        value: Boolean
    ): Result<Answer> {
        val answer = Answer.BooleanAnswer(questionId, answerId, value)
        return Result.Success(answer)
    }

    private fun createDateAnswer(
        questionId: String?,
        answerId: String?,
        value: LinkedTreeMap<*, *>
    ): Result<Answer> {

        // Cast check
        value as LinkedTreeMap<String, Any?>

        val year = (value["year"] as? Double)?.toInt()
        val month = (value["month"] as? Double)?.toInt()?.minus(1)
        val day = (value["day"] as? Double)?.toInt()

        val date = Date(year, month, day)
        val answer = Answer.DateAnswer(questionId, answerId, date)

        return Result.Success(answer)
    }

    private fun createPickListAnswer(
        questionId: String?,
        answerId: String?,
        value: ArrayList<*>
    ): Result<Answer> {
        val pickedOptions: List<PickedOption> = value.mapNotNull { item ->
            if (item is LinkedTreeMap<*, *>) {

                // Cast check
                item as LinkedTreeMap<String, Any?>

                val optionId: String? = item["stableId"] as? String
                val detail: String? = item["detail"] as? String
                if (optionId != null) {
                    PickedOption(optionId, detail)
                } else {
                    null
                }
            } else {
                null
            }
        }

        val answer = Answer.PickListAnswer(questionId, answerId, pickedOptions)
        return Result.Success(answer)
    }

    private fun createCompositeAnswer(
        questionId: String?,
        answerId: String?,
        value: ArrayList<*>
    ): Result<Answer> {
        val answers = value.mapNotNull { items ->
            if (items is ArrayList<*>) {
                items.mapNotNull { item ->
                    if (item is LinkedTreeMap<*, *>) {

                        // Cast check
                        item as LinkedTreeMap<String, Any?>

                        val itemValue: Any? = item["value"]
                        val itemId: String? = item["answerGuid"] as? String
                        val itemType: String? = item["type"] as? String

                        when (val createResult = when {
                            itemType == TEXT && itemValue is String -> createTextAnswer(
                                null,
                                itemId,
                                itemValue
                            )
                            itemType == DATE && itemValue is LinkedTreeMap<*, *> ->
                                createDateAnswer(null, itemId, itemValue)
                            itemType == PICKLIST && itemValue is ArrayList<*> ->
                                createPickListAnswer(null, itemId, itemValue)
                            else -> Result.Failure(MalformedResponseException(this::class))
                        }) {
                            is Result.Success -> createResult.value
                            is Result.Failure -> null
                        }
                    } else {
                        null
                    }
                }
            } else {
                null
            }
        }

        val answer = Answer.CompositeAnswer(questionId, answerId, answers)
        return Result.Success(answer)
    }

    private fun createUnknownAnswer(): Result<Answer> {
        val answer = Answer.UnknownAnswer(questionId, answerId, null)
        return Result.Success(answer)
    }

    companion object {
        private const val TEXT = "TEXT"
        private const val BOOLEAN = "BOOLEAN"
        private const val DATE = "DATE"
        private const val COMPOSITE = "COMPOSITE"
        private const val PICKLIST = "PICKLIST"
    }
}