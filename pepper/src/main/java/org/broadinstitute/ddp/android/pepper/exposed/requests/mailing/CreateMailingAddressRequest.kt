package org.broadinstitute.ddp.android.pepper.exposed.requests.mailing

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.MailingAddress
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class CreateMailingAddressRequest(
    val strict: Boolean,
    val description: String,
    val name: String,
    val street1: String,
    val street2: String?,
    val city: String,
    val state: String,
    val country: String,
    val zip: String,
    val phone: String,
    val isDefault: Boolean
) : PepperRequest<MailingAddress>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<MailingAddress>> = scope.async {
        dataSources.mailingDataSource.createMailingAddress(this@CreateMailingAddressRequest)
    }
}