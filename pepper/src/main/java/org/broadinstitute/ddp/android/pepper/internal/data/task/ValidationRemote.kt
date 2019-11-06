package org.broadinstitute.ddp.android.pepper.internal.data.task

import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.Validation
import org.broadinstitute.ddp.android.pepper.internal.common.Constants
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class ValidationRemote(
    @SerializedName("rule") val rule: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("startDate") val startDate: String?,
    @SerializedName("endDate") val endDate: String?,
    @SerializedName("minAge") val minAge: Int?,
    @SerializedName("maxAge") val maxAge: Int?
) : Mappable<Validation> {
    override fun mapToResult(): Result<Validation> {
        if (rule == null || message == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        return when (rule) {
            Constants.VALIDATION_DATE_RANGE -> createDateRange(rule, message)
            Constants.VALIDATION_REQUIRED -> createRequired(rule, message)
            Constants.VALIDATION_COMPLETE -> createComplete(rule, message)
            Constants.VALIDATION_AGE_RANGE -> createAgeRange(rule, message)
            else -> Result.Failure(MalformedResponseException(this::class))
        }
    }

    private fun createDateRange(rule: String, message: String): Result<Validation> {
        if (startDate == null || endDate == null) {
            return Result.Failure(MalformedResponseException(this::class))
        }

        val validation = Validation.DateRange(rule, message, startDate, endDate)

        return Result.Success(validation)
    }

    private fun createRequired(rule: String, message: String): Result<Validation> {
        val validation = Validation.Required(rule, message)

        return Result.Success(validation)
    }

    private fun createComplete(rule: String, message: String): Result<Validation> {
        val validation = Validation.Complete(rule, message)

        return Result.Success(validation)
    }

    private fun createAgeRange(rule: String, message: String): Result<Validation> {
        val validation = Validation.AgeRange(rule, message, minAge, maxAge)

        return Result.Success(validation)
    }
}
