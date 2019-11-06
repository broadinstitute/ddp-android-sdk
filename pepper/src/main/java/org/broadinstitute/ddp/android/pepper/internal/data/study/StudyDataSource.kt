package org.broadinstitute.ddp.android.pepper.internal.data.study

import org.broadinstitute.ddp.android.pepper.exposed.models.Drug
import org.broadinstitute.ddp.android.pepper.exposed.models.ParticipantStatus
import org.broadinstitute.ddp.android.pepper.exposed.models.Study
import org.broadinstitute.ddp.android.pepper.exposed.requests.study.GetDrugSuggestionsRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.study.GetParticipantStatusRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.study.GetStudiesRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.study.GetStudyRequest
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal interface StudyDataSource {

    suspend fun getStudies(request: GetStudiesRequest): Result<List<Study>>

    suspend fun getStudy(request: GetStudyRequest): Result<Study>

    suspend fun getParticipantStatus(request: GetParticipantStatusRequest): Result<ParticipantStatus>

    suspend fun getDrugSuggestions(request: GetDrugSuggestionsRequest): Result<List<Drug>>
}