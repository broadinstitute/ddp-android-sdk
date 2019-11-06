package org.broadinstitute.ddp.android.pepper.internal.common

import android.content.Context
import org.broadinstitute.ddp.android.pepper.exposed.PepperAuthenticator
import org.broadinstitute.ddp.android.pepper.internal.common.modules.BASE_URL
import org.broadinstitute.ddp.android.pepper.internal.common.modules.CONTEXT
import org.broadinstitute.ddp.android.pepper.internal.common.modules.PEPPER_AUTHENTICATOR
import org.broadinstitute.ddp.android.pepper.internal.common.modules.dataSourceModule
import org.broadinstitute.ddp.android.pepper.internal.common.modules.remoteDataModule
import org.broadinstitute.ddp.android.pepper.internal.data.consent.ConsentDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.mailing.MailingDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.medicalprovider.MedicalDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.study.StudyDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.task.TaskDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.user.UserDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.workflow.WorkflowDataSource
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.KoinComponent
import org.koin.core.inject

internal class DataSources(
    context: Context,
    baseUrl: String,
    pepperAuthenticator: PepperAuthenticator
) : KoinComponent {

    private val koinApplication = KoinApplication.create().apply {
        val properties = HashMap<String, Any>()
        properties[CONTEXT] = context
        properties[BASE_URL] = baseUrl
        properties[PEPPER_AUTHENTICATOR] = pepperAuthenticator
        properties(properties)

        val modules = listOf(dataSourceModule, remoteDataModule)
        modules(modules)
    }

    val userDataSource: UserDataSource by inject()

    val taskDataSource: TaskDataSource by inject()

    val medicalDataSource: MedicalDataSource by inject()

    val workflowDataSource: WorkflowDataSource by inject()

    val consentDataSource: ConsentDataSource by inject()

    val studyDataSource: StudyDataSource by inject()

    val mailingDataSource: MailingDataSource by inject()

    override fun getKoin(): Koin = koinApplication.koin
}