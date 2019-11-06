package org.broadinstitute.ddp.android.pepper.exposed.requests.study

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.ParticipantStatus
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class GetParticipantStatusRequest(val studyId: String) : PepperRequest<ParticipantStatus>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<ParticipantStatus>> =
        scope.async {
            dataSources.studyDataSource.getParticipantStatus(this@GetParticipantStatusRequest)
        }
}