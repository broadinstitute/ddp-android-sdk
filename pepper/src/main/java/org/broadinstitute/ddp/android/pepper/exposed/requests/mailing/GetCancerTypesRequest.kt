package org.broadinstitute.ddp.android.pepper.exposed.requests.mailing

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.CancerType
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class GetCancerTypesRequest : PepperRequest<List<CancerType>>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<List<CancerType>>> = scope.async {
        dataSources.mailingDataSource.getCancerTypes(this@GetCancerTypesRequest)
    }
}