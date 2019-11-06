package org.broadinstitute.ddp.android.pepper.internal.common.modules

import org.broadinstitute.ddp.android.pepper.internal.data.consent.ConsentDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.consent.ConsentRemoteDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.mailing.MailingDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.mailing.MailingRemoteDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.medicalprovider.MedicalDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.medicalprovider.MedicalRemoteDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.study.StudyDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.study.StudyRemoteDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.task.TaskDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.task.TaskRemoteDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.user.UserDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.user.UserRemoteDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.workflow.WorkflowDataSource
import org.broadinstitute.ddp.android.pepper.internal.data.workflow.WorkflowRemoteDataSource
import org.koin.dsl.module

const val PEPPER_AUTHENTICATOR = "PEPPER_AUTHENTICATOR"

internal val dataSourceModule = module {
    single<UserDataSource> { UserRemoteDataSource(get(), getProperty(PEPPER_AUTHENTICATOR)) }
    single<TaskDataSource> { TaskRemoteDataSource(get(), getProperty(PEPPER_AUTHENTICATOR)) }
    single<MedicalDataSource> { MedicalRemoteDataSource(get(), getProperty(PEPPER_AUTHENTICATOR)) }
    single<WorkflowDataSource> {
        WorkflowRemoteDataSource(
            get(),
            getProperty(PEPPER_AUTHENTICATOR)
        )
    }
    single<ConsentDataSource> { ConsentRemoteDataSource(get(), getProperty(PEPPER_AUTHENTICATOR)) }
    single<StudyDataSource> { StudyRemoteDataSource(get(), getProperty(PEPPER_AUTHENTICATOR)) }
    single<MailingDataSource> { MailingRemoteDataSource(get(), getProperty(PEPPER_AUTHENTICATOR)) }
}