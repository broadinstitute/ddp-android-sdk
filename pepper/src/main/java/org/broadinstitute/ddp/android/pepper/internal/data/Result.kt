package org.broadinstitute.ddp.android.pepper.internal.data

internal sealed class Result<T> {

    abstract val value: T?

    data class Success<T>(override val value: T) : Result<T>()

    data class Failure<T>(val error: Exception, override val value: T? = null) : Result<T>()
}