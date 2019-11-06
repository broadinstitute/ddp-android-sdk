package org.broadinstitute.ddp.android.pepper.exposed.requests.medicalprovider

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.InstitutionType
import org.broadinstitute.ddp.android.pepper.exposed.models.MedicalProvider
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class CreateMedicalProviderRequest(
    val studyId: String,
    val institutionType: InstitutionType,
    val institutionName: String?,
    val physicianName: String?,
    val city: String?,
    val state: String?
) : PepperRequest<MedicalProvider>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<MedicalProvider>> = scope.async {
        dataSources.medicalDataSource.createMedicalProvider(this@CreateMedicalProviderRequest)
    }
}