package org.broadinstitute.ddp.android.pepper.exposed.requests.mailing

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.Country
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class GetCountriesRequest : PepperRequest<List<Country>>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<List<Country>>> = scope.async {
        dataSources.mailingDataSource.getCountries(this@GetCountriesRequest)
    }
}