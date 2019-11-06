package org.broadinstitute.ddp.android.pepper.internal.common.okhttp

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import org.broadinstitute.ddp.android.pepper.exposed.exceptions.NoConnectionException
import org.broadinstitute.ddp.android.pepper.internal.common.extensions.isNetworkAvailable

internal class ConnectivityInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!context.isNetworkAvailable()) throw NoConnectionException()
        return chain.proceed(chain.request())
    }
}