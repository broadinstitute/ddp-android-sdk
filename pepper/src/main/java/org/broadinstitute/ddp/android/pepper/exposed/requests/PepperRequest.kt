package org.broadinstitute.ddp.android.pepper.exposed.requests

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

abstract class PepperRequest<T> {
    internal abstract fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<T>>
}