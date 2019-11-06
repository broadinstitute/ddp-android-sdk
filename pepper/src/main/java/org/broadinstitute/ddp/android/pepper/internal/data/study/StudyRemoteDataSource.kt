package org.broadinstitute.ddp.android.pepper.internal.data.study

import org.broadinstitute.ddp.android.pepper.exposed.PepperAuthenticator
import org.broadinstitute.ddp.android.pepper.exposed.models.Drug
import org.broadinstitute.ddp.android.pepper.exposed.models.ParticipantStatus
import org.broadinstitute.ddp.android.pepper.exposed.models.Study
import org.broadinstitute.ddp.android.pepper.exposed.requests.study.GetDrugSuggestionsRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.study.GetParticipantStatusRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.study.GetStudiesRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.study.GetStudyRequest
import org.broadinstitute.ddp.android.pepper.internal.common.tryForResult
import org.broadinstitute.ddp.android.pepper.internal.data.PepperService
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal class StudyRemoteDataSource(
    private val pepperService: PepperService,
    private val pepperAuthenticator: PepperAuthenticator
) : StudyDataSource {

    override suspend fun getStudies(request: GetStudiesRequest): Result<List<Study>> =
        tryForResult {
            pepperService.getStudiesAsync(request.umbrella)
                .await()
                .mapNotNull { study ->
                    when (val mapResult = study.mapToResult()) {
                        is Result.Success -> mapResult.value
                        is Result.Failure -> null
                    }
                }
                .let { studies -> Result.Success(studies) }
        }

    override suspend fun getStudy(request: GetStudyRequest): Result<Study> = tryForResult {
        pepperService.getStudyAsync(request.studyId)
            .await()
            .mapToResult()
    }

    override suspend fun getParticipantStatus(request: GetParticipantStatusRequest): Result<ParticipantStatus> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId()
            val studyId = request.studyId

            pepperService.getParticipantStatusAsync(userId, studyId)
                .await()
                .mapToResult()
        }

    override suspend fun getDrugSuggestions(request: GetDrugSuggestionsRequest): Result<List<Drug>> =
        tryForResult {
            val studyId = request.studyId
            val query = request.query
            val limit = request.limit

            pepperService
                .getDrugSuggestionsAsync(studyId, query, limit)
                .await()
                .results
                .mapNotNull { result ->
                    when (val mapResult = result.drug.mapToResult()) {
                        is Result.Success -> mapResult.value
                        is Result.Failure -> null
                    }
                }
                .let { drugs -> Result.Success(drugs) }
        }
}