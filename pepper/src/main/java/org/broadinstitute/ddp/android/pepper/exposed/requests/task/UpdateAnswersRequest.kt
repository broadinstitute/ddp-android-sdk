package org.broadinstitute.ddp.android.pepper.exposed.requests.task

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.Answer
import org.broadinstitute.ddp.android.pepper.exposed.models.UpdateAnswersResponse
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class UpdateAnswersRequest(
    val studyId: String,
    val instanceId: String,
    val tempUserId: String?,
    val answers: List<Answer>
) : PepperRequest<UpdateAnswersResponse>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<UpdateAnswersResponse>> =
        scope.async {
            dataSources.taskDataSource.updateAnswers(this@UpdateAnswersRequest)
        }
}