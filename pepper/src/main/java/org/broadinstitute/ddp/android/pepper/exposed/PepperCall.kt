package org.broadinstitute.ddp.android.pepper.exposed

interface PepperCall<T> {

    fun start(pepperCallback: PepperCallback<T>)

    fun execute(): PepperResponse<T>

    fun cancel()
}