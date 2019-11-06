package org.broadinstitute.ddp.android.pepper.internal.common.extensions

import org.broadinstitute.ddp.android.pepper.exposed.PepperResponse
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal fun <T> Result<T>.toResponse(): PepperResponse<T> = when (this) {
    is Result.Success -> PepperResponse.Success(value)
    is Result.Failure -> PepperResponse.Failure(error)
}