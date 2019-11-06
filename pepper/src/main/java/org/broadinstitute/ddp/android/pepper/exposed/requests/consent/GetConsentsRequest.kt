package org.broadinstitute.ddp.android.pepper.exposed.requests.consent

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.Consent
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class GetConsentsRequest(val studyId: String) : PepperRequest<List<Consent>>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<List<Consent>>> =
        scope.async {
            dataSources.consentDataSource.getConsents(this@GetConsentsRequest)
        }
}