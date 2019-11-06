package org.broadinstitute.ddp.android.pepper.internal.data

internal interface Mappable<T> {

    fun mapToResult(): Result<T>
}