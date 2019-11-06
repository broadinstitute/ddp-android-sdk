package org.broadinstitute.ddp.android.pepper.exposed.requests.medicalprovider

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.Institution
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class SearchInstitutionsRequest(val namePattern: String) : PepperRequest<List<Institution>>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<List<Institution>>> =
        scope.async {
            dataSources.medicalDataSource.searchInstitutions(this@SearchInstitutionsRequest)
        }
}