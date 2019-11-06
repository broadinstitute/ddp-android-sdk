package org.broadinstitute.ddp.android.pepper.exposed.requests.study

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.Study
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class GetStudiesRequest(val umbrella: String) : PepperRequest<List<Study>>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<List<Study>>> =
        scope.async {
            dataSources.studyDataSource.getStudies(this@GetStudiesRequest)
        }
}