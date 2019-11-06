package org.broadinstitute.ddp.android.pepper.exposed.requests.user

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.TemporaryUser
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class CreateTemporaryUserRequest(val auth0ClientId: String) : PepperRequest<TemporaryUser>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<TemporaryUser>> =
        scope.async {
            dataSources.userDataSource.createTemporaryUser(this@CreateTemporaryUserRequest)
        }
}