package org.broadinstitute.ddp.android.pepper.exposed.requests.medicalprovider

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.InstitutionType
import org.broadinstitute.ddp.android.pepper.exposed.models.MedicalProvider
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class GetMedicalProvidersRequest(
    val studyId: String,
    val institutionType: InstitutionType
) : PepperRequest<List<MedicalProvider>>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<List<MedicalProvider>>> = scope.async {
        dataSources.medicalDataSource.getMedicalProviders(this@GetMedicalProvidersRequest)
    }
}