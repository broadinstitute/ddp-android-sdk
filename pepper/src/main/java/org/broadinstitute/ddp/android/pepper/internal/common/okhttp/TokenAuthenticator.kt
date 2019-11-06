package org.broadinstitute.ddp.android.pepper.internal.common.okhttp

import com.auth0.android.jwt.JWT
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.broadinstitute.ddp.android.pepper.exposed.PepperAuthenticator
import org.broadinstitute.ddp.android.pepper.exposed.PepperResponse
import org.broadinstitute.ddp.android.pepper.internal.common.extensions.authorization
import java.io.IOException

internal class TokenAuthenticator(private val pepperAuthenticator: PepperAuthenticator) :
    Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? = try {
        when (val refreshTokenResponse = pepperAuthenticator.refreshToken()) {
            is PepperResponse.Success -> response.request
                .newBuilder()
                .header("Authorization", JWT(refreshTokenResponse.value).authorization())
                .build()

            is PepperResponse.Failure -> throw refreshTokenResponse.error
        }
    } catch (error: Exception) {
        throw IOException(error)
    }
}