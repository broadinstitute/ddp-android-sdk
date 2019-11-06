package org.broadinstitute.ddp.android.pepper.internal.data.consent

import org.broadinstitute.ddp.android.pepper.exposed.PepperAuthenticator
import org.broadinstitute.ddp.android.pepper.exposed.models.Consent
import org.broadinstitute.ddp.android.pepper.exposed.requests.consent.GetConsentRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.consent.GetConsentsRequest
import org.broadinstitute.ddp.android.pepper.internal.common.tryForResult
import org.broadinstitute.ddp.android.pepper.internal.data.PepperService
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal class ConsentRemoteDataSource(
    private val pepperService: PepperService,
    private val pepperAuthenticator: PepperAuthenticator
) : ConsentDataSource {

    override suspend fun getConsents(request: GetConsentsRequest): Result<List<Consent>> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId()
            val studyId = request.studyId

            pepperService.getConsentsAsync(userId, studyId)
                .await()
                .mapNotNullTo(ArrayList()) { consentRemote ->
                    when (val mapResult = consentRemote.mapToResult()) {
                        is Result.Success -> mapResult.value
                        else -> null
                    }
                }.let { consents -> Result.Success(consents.toList()) }
        }

    override suspend fun getConsent(request: GetConsentRequest): Result<Consent> = tryForResult {
        val userId = pepperAuthenticator.getUserId()
        val studyId = request.studyId
        val taskCode = request.taskCode

        pepperService.getConsentAsync(userId, studyId, taskCode)
            .await()
            .mapToResult()
    }
}