package org.broadinstitute.ddp.android.pepper.exposed.requests.medicalprovider

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.broadinstitute.ddp.android.pepper.exposed.models.InstitutionType
import org.broadinstitute.ddp.android.pepper.exposed.requests.PepperRequest
import org.broadinstitute.ddp.android.pepper.internal.common.DataSources
import org.broadinstitute.ddp.android.pepper.internal.data.Result

class DeleteMedicalProviderRequest(
    val studyId: String,
    val institutionType: InstitutionType,
    val medicalProviderId: String
) : PepperRequest<Unit>() {

    override fun getResultAsync(
        scope: CoroutineScope,
        dataSources: DataSources
    ): Deferred<Result<Unit>> = scope.async {
        dataSources.medicalDataSource.deleteMedicalProvider(this@DeleteMedicalProviderRequest)
    }
}