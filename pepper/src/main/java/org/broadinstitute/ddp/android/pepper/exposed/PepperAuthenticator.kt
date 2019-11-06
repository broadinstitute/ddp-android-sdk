package org.broadinstitute.ddp.android.pepper.exposed

import com.auth0.android.jwt.JWT
import org.broadinstitute.ddp.android.pepper.internal.common.extensions.userId

interface PepperAuthenticator {

    fun getToken(): PepperResponse<String>

    fun refreshToken(): PepperResponse<String>

    fun getUserId(): String? = when (val getTokenResult = getToken()) {
        is PepperResponse.Success -> JWT(getTokenResult.value).userId()
        is PepperResponse.Failure -> null
    }
}