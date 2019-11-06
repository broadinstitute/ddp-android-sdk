package org.broadinstitute.ddp.android.pepper.internal.data.medicalprovider

import org.broadinstitute.ddp.android.pepper.exposed.models.Institution
import org.broadinstitute.ddp.android.pepper.exposed.models.MedicalProvider
import org.broadinstitute.ddp.android.pepper.exposed.requests.medicalprovider.CreateMedicalProviderRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.medicalprovider.DeleteMedicalProviderRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.medicalprovider.GetMedicalProvidersRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.medicalprovider.SearchInstitutionsRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.medicalprovider.UpdateMedicalProviderRequest
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal interface MedicalDataSource {

    suspend fun getMedicalProviders(request: GetMedicalProvidersRequest): Result<List<MedicalProvider>>

    suspend fun createMedicalProvider(request: CreateMedicalProviderRequest): Result<MedicalProvider>

    suspend fun updateMedicalProvider(request: UpdateMedicalProviderRequest): Result<MedicalProvider>

    suspend fun deleteMedicalProvider(request: DeleteMedicalProviderRequest): Result<Unit>

    suspend fun searchInstitutions(request: SearchInstitutionsRequest): Result<List<Institution>>
}