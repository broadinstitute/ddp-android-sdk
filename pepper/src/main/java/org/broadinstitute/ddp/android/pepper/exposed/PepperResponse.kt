package org.broadinstitute.ddp.android.pepper.exposed

sealed class PepperResponse<T> {

    abstract val value: T?

    data class Success<T>(override val value: T) : PepperResponse<T>()

    data class Failure<T>(val error: Exception, override val value: T? = null) : PepperResponse<T>()
}