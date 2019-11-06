package org.broadinstitute.ddp.android.pepper.exposed.requests.mailing

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.MailingAddress
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class GetMailingAddressRequest(
    val addressId: String
) : PepperRequest<MailingAddress>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<MailingAddress>> = scope.async {
        dataSources.mailingDataSource.getMailingAddress(this@GetMailingAddressRequest)
    }
}