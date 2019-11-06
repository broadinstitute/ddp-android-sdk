package org.broadinstitute.ddp.android.pepper.internal.common.okhttp

import com.auth0.android.jwt.JWT
import okhttp3.Interceptor
import okhttp3.Response
import org.broadinstitute.ddp.android.pepper.exposed.PepperAuthenticator
import org.broadinstitute.ddp.android.pepper.exposed.PepperResponse
import org.broadinstitute.ddp.android.pepper.internal.common.extensions.authorization
import java.io.IOException

internal class TokenInterceptor(private val pepperAuthenticator: PepperAuthenticator) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = try {
        val request = when (val getTokenResponse = pepperAuthenticator.getToken()) {
            is PepperResponse.Success -> chain.request().newBuilder()
                .header("Authorization", JWT(getTokenResponse.value).authorization())
                .build()

            is PepperResponse.Failure -> chain.request()
        }

        chain.proceed(request)
    } catch (error: Exception) {
        throw IOException(error)
    }
}