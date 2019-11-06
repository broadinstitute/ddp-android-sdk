package org.broadinstitute.ddp.android.pepper.exposed.requests.mailing

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class UpdateMailingAddressRequest(
    val strict: Boolean,
    val addressId: String,
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
) : PepperRequest<Unit>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<Unit>> = scope.async {
        dataSources.mailingDataSource.updateMailingAddress(this@UpdateMailingAddressRequest)
    }
}