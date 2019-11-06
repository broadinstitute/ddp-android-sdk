package org.broadinstitute.ddp.android.pepper.internal.common

import kotlinx.coroutines.runBlocking
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal fun <T> tryForResult(block: suspend () -> Result<T>): Result<T> = runBlocking {
    try {
        block()
    } catch (error: Exception) {
        Result.Failure<T>(error)
    }
}