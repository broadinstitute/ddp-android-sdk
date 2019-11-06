package org.broadinstitute.ddp.android.pepper.exposed.requests.mailing

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class AddToMailingListRequest(
    val studyId: String,
    val firstName: String,
    val lastName: String,
    val email: String
) : PepperRequest<Unit>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<Unit>> =
        scope.async {
            dataSources.mailingDataSource.addToMailingList(this@AddToMailingListRequest)
        }
}