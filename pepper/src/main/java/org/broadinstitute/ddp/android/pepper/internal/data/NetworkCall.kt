package org.broadinstitute.ddp.android.pepper.internal.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.broadinstitute.ddp.android.pepper.exposed.PepperCall
import org.broadinstitute.ddp.android.pepper.exposed.PepperCallback
import org.broadinstitute.ddp.android.pepper.exposed.PepperResponse
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.common.extensions.onResult
import org.broadinstitute.ddp.android.pepper.internal.common.extensions.toResponse
import kotlin.coroutines.CoroutineContext

internal class NetworkCall<T>(
    private val request: PepperRequest<T>,
    private val dataSources: DataSources
) : PepperCall<T>, CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun start(pepperCallback: PepperCallback<T>) {
        launch {
            val result = withContext(Dispatchers.Default) {
                request.getResultAsync(this, dataSources).await()
            }

            pepperCallback.onResult(result)
        }
    }

    override fun execute(): PepperResponse<T> = runBlocking {
        request.getResultAsync(this, dataSources).await().toResponse()
    }

    override fun cancel() {
        job.cancel()
    }
}