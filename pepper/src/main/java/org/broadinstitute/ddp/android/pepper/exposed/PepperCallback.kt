package org.broadinstitute.ddp.android.pepper.exposed

interface PepperCallback<T> {

    fun onSuccess(data: T)

    fun onError(error: Exception)
}