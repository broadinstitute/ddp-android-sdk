package org.broadinstitute.ddp.android.pepper.exposed.requests.user

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class UpdateEmailRequest(val email: String) : PepperRequest<Unit>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<Unit>> =
        scope.async {
            dataSources.userDataSource.updateEmail(this@UpdateEmailRequest)
        }
}