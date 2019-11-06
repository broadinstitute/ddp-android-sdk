package org.broadinstitute.ddp.android.pepper.exposed.requests.user

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.Participant
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class CreateParticipantRequest(val alias: String) : PepperRequest<Participant>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<Participant>> =
        scope.async {
            dataSources.userDataSource.createParticipant(this@CreateParticipantRequest)
        }
}