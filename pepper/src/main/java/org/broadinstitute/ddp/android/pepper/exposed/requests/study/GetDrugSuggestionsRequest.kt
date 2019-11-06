package org.broadinstitute.ddp.android.pepper.exposed.requests.study

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.Drug
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class GetDrugSuggestionsRequest(val studyId: String, val query: String, val limit: Int) :
    PepperRequest<List<Drug>>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<List<Drug>>> =
        scope.async {
            dataSources.studyDataSource.getDrugSuggestions(this@GetDrugSuggestionsRequest)
        }
}