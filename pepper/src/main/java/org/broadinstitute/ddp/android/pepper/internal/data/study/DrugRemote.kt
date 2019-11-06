package org.broadinstitute.ddp.android.pepper.internal.data.study

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.MalformedResponseException
import org.broadinstitute.ddp.android.pepper.exposed.models.Drug
import org.broadinstitute.ddp.android.pepper.internal.data.Mappable
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal data class DrugRemote(
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?
) : Mappable<Drug> {

    @SuppressLint("DefaultLocale")
    override fun mapToResult(): Result<Drug> {
        if (name == null) return Result.Failure(MalformedResponseException(this::class))

        val name = name.toLowerCase().capitalize()
        val drug = Drug(name, description)

        return Result.Success(drug)
    }
}