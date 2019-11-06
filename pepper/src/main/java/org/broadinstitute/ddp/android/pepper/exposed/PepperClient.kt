package org.broadinstitute.ddp.android.pepper.exposed

import android.content.Context
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.NetworkCall

class PepperClient(context: Context, baseUrl: String, pepperAuthenticator: PepperAuthenticator) {

    private val dataSources: DataSources = DataSources(context, baseUrl, pepperAuthenticator)

    fun <T> request(request: PepperRequest<T>): PepperCall<T> = NetworkCall(request, dataSources)
}
