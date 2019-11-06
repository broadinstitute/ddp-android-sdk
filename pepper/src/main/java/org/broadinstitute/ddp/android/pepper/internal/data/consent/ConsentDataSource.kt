package org.broadinstitute.ddp.android.pepper.internal.data.consent

import org.broadinstitute.ddp.android.pepper.exposed.models.Consent
import org.broadinstitute.ddp.android.pepper.exposed.requests.consent.GetConsentRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.consent.GetConsentsRequest
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal interface ConsentDataSource {

    suspend fun getConsents(request: GetConsentsRequest): Result<List<Consent>>

    suspend fun getConsent(request: GetConsentRequest): Result<Consent>
}