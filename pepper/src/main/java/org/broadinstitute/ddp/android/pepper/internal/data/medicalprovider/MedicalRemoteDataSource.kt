package org.broadinstitute.ddp.android.pepper.internal.data.medicalprovider

import org.broadinstitute.ddp.android.pepper.exposed.PepperAuthenticator
import org.broadinstitute.ddp.android.pepper.exposed.models.Institution
import org.broadinstitute.ddp.android.pepper.exposed.models.MedicalProvider
import org.broadinstitute.ddp.android.pepper.exposed.requests.medicalprovider.CreateMedicalProviderRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.medicalprovider.DeleteMedicalProviderRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.medicalprovider.GetMedicalProvidersRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.medicalprovider.SearchInstitutionsRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.medicalprovider.UpdateMedicalProviderRequest
import org.broadinstitute.ddp.android.pepper.internal.common.tryForResult
import org.broadinstitute.ddp.android.pepper.internal.data.PepperService
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal class MedicalRemoteDataSource(
    private val pepperService: PepperService,
    private val pepperAuthenticator: PepperAuthenticator
) : MedicalDataSource {

    override suspend fun getMedicalProviders(request: GetMedicalProvidersRequest): Result<List<MedicalProvider>> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId()
            val studyId = request.studyId
            val institutionType = request.institutionType.path

            pepperService.getMedicalProvidersAsync(userId, studyId, institutionType)
                .await()
                .mapNotNullTo(ArrayList()) { medicalProviderRemote ->
                    when (val mapResult = medicalProviderRemote.mapToResult()) {
                        is Result.Success -> mapResult.value
                        is Result.Failure -> null
                    }
                }.let { medicalProviders -> Result.Success(medicalProviders.toList()) }
        }

    override suspend fun createMedicalProvider(request: CreateMedicalProviderRequest): Result<MedicalProvider> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId()
            val studyId = request.studyId
            val institutionType = request.institutionType.path
            val institutionName = request.institutionName
            val physicianName = request.physicianName
            val city = request.city
            val state = request.state

            val body = MedicalProviderRemote(null, institutionName, physicianName, city, state)

            val response = pepperService
                .createMedicalProviderAsync(userId, studyId, institutionType, body)
                .await()

            val medicalProvider = when (val mapResult = response.mapToResult()) {
                is Result.Success -> MedicalProvider(
                    mapResult.value,
                    institutionName,
                    physicianName,
                    city,
                    state
                )
                is Result.Failure -> throw mapResult.error
            }

            Result.Success(medicalProvider)
        }

    override suspend fun updateMedicalProvider(request: UpdateMedicalProviderRequest): Result<MedicalProvider> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId()
            val studyId = request.studyId
            val institutionType = request.institutionType.path
            val medicalProviderId = request.medicalProviderId
            val institutionName = request.institutionName
            val physicianName = request.physicianName
            val city = request.city
            val state = request.state

            val body = MedicalProviderRemote(
                medicalProviderId,
                institutionName,
                physicianName,
                city,
                state
            )

            pepperService
                .updateMedicalProviderAsync(
                    userId,
                    studyId,
                    institutionType,
                    medicalProviderId,
                    body
                )
                .await()

            val medicalProvider =
                MedicalProvider(medicalProviderId, institutionName, physicianName, city, state)
            Result.Success(medicalProvider)
        }

    override suspend fun deleteMedicalProvider(request: DeleteMedicalProviderRequest): Result<Unit> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId()
            val studyId = request.studyId
            val institutionType = request.institutionType.path
            val medicalProviderId = request.medicalProviderId

            pepperService
                .deleteMedicalProviderAsync(userId, studyId, institutionType, medicalProviderId)
                .await()
                .let { unit -> Result.Success(unit) }
        }

    override suspend fun searchInstitutions(request: SearchInstitutionsRequest): Result<List<Institution>> =
        tryForResult {
            val namePattern = request.namePattern

            pepperService.getInstitutionAutocompleteAsync(namePattern)
                .await()
                .mapNotNullTo(ArrayList()) { institutionRemote ->
                    when (val mapResult = institutionRemote.mapToResult()) {
                        is Result.Success -> mapResult.value
                        is Result.Failure -> null
                    }
                }
                .let { institutions -> Result.Success(institutions.toList()) }
        }
}
