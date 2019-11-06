package org.broadinstitute.ddp.android.pepper.internal.common.extensions

import org.broadinstitute.ddp.android.pepper.exposed.PepperCallback
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal fun <T> PepperCallback<T>.onResult(result: Result<T>) {
    when (result) {
        is Result.Success -> this.onSuccess(result.value)
        is Result.Failure -> this.onError(result.error)
    }
}