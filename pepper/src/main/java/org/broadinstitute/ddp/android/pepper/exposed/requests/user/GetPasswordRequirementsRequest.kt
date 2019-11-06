package org.broadinstitute.ddp.android.pepper.exposed.requests.user

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.PasswordRequirements
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class GetPasswordRequirementsRequest(val studyId: String) : PepperRequest<PasswordRequirements>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<PasswordRequirements>> = scope.async {
        dataSources.userDataSource.getPasswordRequirements(this@GetPasswordRequirementsRequest)
    }
}