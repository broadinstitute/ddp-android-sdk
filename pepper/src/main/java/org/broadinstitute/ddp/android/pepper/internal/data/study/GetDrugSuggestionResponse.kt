package org.broadinstitute.ddp.android.pepper.internal.data.study

import com.google.gson.annotations.SerializedName

internal data class GetDrugSuggestionResponse(
    @SerializedName("results") val results: List<GetDrugSuggestionResult>
) {
    internal data class GetDrugSuggestionResult(@SerializedName("drug") val drug: DrugRemote)
}