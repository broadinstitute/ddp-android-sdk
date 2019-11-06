package org.broadinstitute.ddp.android.pepper.internal.data

import kotlinx.coroutines.Deferred
import org.broadinstitute.ddp.android.pepper.internal.data.consent.ConsentRemote
import org.broadinstitute.ddp.android.pepper.internal.data.mailing.AddToMailingListBody
import org.broadinstitute.ddp.android.pepper.internal.data.mailing.CancerTypeRemote
import org.broadinstitute.ddp.android.pepper.internal.data.mailing.CountryRemote
import org.broadinstitute.ddp.android.pepper.internal.data.mailing.MailingAddressRemote
import org.broadinstitute.ddp.android.pepper.internal.data.medicalprovider.CreateMedicalProviderResponse
import org.broadinstitute.ddp.android.pepper.internal.data.medicalprovider.InstitutionRemote
import org.broadinstitute.ddp.android.pepper.internal.data.medicalprovider.MedicalProviderRemote
import org.broadinstitute.ddp.android.pepper.internal.data.study.GetDrugSuggestionResponse
import org.broadinstitute.ddp.android.pepper.internal.data.study.ParticipantStatusRemote
import org.broadinstitute.ddp.android.pepper.internal.data.study.StudyRemote
import org.broadinstitute.ddp.android.pepper.internal.data.task.CompleteTaskResponseRemote
import org.broadinstitute.ddp.android.pepper.internal.data.task.DashboardTaskRemote
import org.broadinstitute.ddp.android.pepper.internal.data.task.TaskInstanceRemote
import org.broadinstitute.ddp.android.pepper.internal.data.task.TaskSummaryRemote
import org.broadinstitute.ddp.android.pepper.internal.data.task.UpdateAnswersBody
import org.broadinstitute.ddp.android.pepper.internal.data.task.UpdateAnswersResponseRemote
import org.broadinstitute.ddp.android.pepper.internal.data.user.CreateParticipantBody
import org.broadinstitute.ddp.android.pepper.internal.data.user.CreateParticipantResponse
import org.broadinstitute.ddp.android.pepper.internal.data.user.CreateTemporaryUserBody
import org.broadinstitute.ddp.android.pepper.internal.data.user.CreateTemporaryUserResponse
import org.broadinstitute.ddp.android.pepper.internal.data.user.GetParticipantsResponse
import org.broadinstitute.ddp.android.pepper.internal.data.user.PasswordRequirementsRemote
import org.broadinstitute.ddp.android.pepper.internal.data.user.UpdateEmailBody
import org.broadinstitute.ddp.android.pepper.internal.data.user.UpdatePasswordBody
import org.broadinstitute.ddp.android.pepper.internal.data.user.UserProfileRemote
import org.broadinstitute.ddp.android.pepper.internal.data.user.WithdrawFromStudyBody
import org.broadinstitute.ddp.android.pepper.internal.data.workflow.WorkflowRemote
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PepperService {

    @GET("/pepper/v1/user/{userId}/profile")
    fun getUserProfileAsync(
        @Path("userId") userId: String?
    ): Deferred<UserProfileRemote>

    @PATCH("/pepper/v1/user/{userId}/profile")
    fun updateUserProfileAsync(
        @Path("userId") userId: String?,
        @Body body: UserProfileRemote
    ): Deferred<UserProfileRemote>

    @GET("/pepper/v1/user/{userId}/studies/{studyId}/activities")
    fun getTaskSummariesAsync(
        @Path("userId") userId: String?,
        @Path("studyId") studyId: String
    ): Deferred<List<TaskSummaryRemote>>

    @GET("/pepper/v1/user/{userId}/studies/{studyId}/dashboard/activity-instances")
    fun getDashboardTasksAsync(
        @Path("userId") userId: String?,
        @Path("studyId") studyId: String
    ): Deferred<List<DashboardTaskRemote>>

    @GET("/pepper/v1/user/{userId}/studies/{studyId}/activities/{instanceId}")
    fun getTaskInstanceAsync(
        @Path("userId") userId: String?,
        @Path("studyId") studyId: String,
        @Path("instanceId") instanceId: String
    ): Deferred<TaskInstanceRemote>

    @GET("/pepper/v1/user/{userId}/studies/{studyId}/medical-providers/{institutionType}")
    fun getMedicalProvidersAsync(
        @Path("userId") userId: String?,
        @Path("studyId") studyId: String,
        @Path("institutionType") institutionType: String
    ): Deferred<List<MedicalProviderRemote>>

    @POST("/pepper/v1/user/{userId}/studies/{studyId}/medical-providers/{institutionType}")
    fun createMedicalProviderAsync(
        @Path("userId") userId: String?,
        @Path("studyId") studyId: String,
        @Path("institutionType") institutionType: String,
        @Body body: MedicalProviderRemote
    ): Deferred<CreateMedicalProviderResponse>

    @PATCH("/pepper/v1/user/{userId}/studies/{studyId}/medical-providers/{institutionType}/{medicalProviderId}")
    fun updateMedicalProviderAsync(
        @Path("userId") userId: String?,
        @Path("studyId") studyId: String,
        @Path("institutionType") institutionType: String,
        @Path("medicalProviderId") medicalProviderId: String,
        @Body body: MedicalProviderRemote
    ): Deferred<Unit>

    @DELETE("/pepper/v1/user/{userId}/studies/{studyId}/medical-providers/{institutionType}/{medicalProviderId}")
    fun deleteMedicalProviderAsync(
        @Path("userId") userId: String?,
        @Path("studyId") studyId: String,
        @Path("institutionType") institutionType: String,
        @Path("medicalProviderId") medicalProviderId: String
    ): Deferred<Unit>

    @GET("/pepper/v1/user/{userId}/studies/{studyId}/workflow")
    fun getWorkflowAsync(
        @Path("userId") userId: String?,
        @Path("studyId") studyId: String,
        @Query("from") state: String,
        @Query("instanceGuid") instanceId: String?,
        @Query("activityCode") taskCode: String?
    ): Deferred<WorkflowRemote>

    @GET("/pepper/v1/user/{userId}/studies/{studyId}/consents")
    fun getConsentsAsync(
        @Path("userId") userId: String?,
        @Path("studyId") studyId: String
    ): Deferred<List<ConsentRemote>>

    @GET("/pepper/v1/user/{userId}/studies/{studyId}/consents/{activityCode}")
    fun getConsentAsync(
        @Path("userId") userId: String?,
        @Path("studyId") studyId: String,
        @Path("activityCode") taskCode: String
    ): Deferred<ConsentRemote>

    @PATCH("/pepper/v1/user/{userId}/studies/{studyId}/activities/{instanceId}/answers")
    fun updateAnswersAsync(
        @Path("userId") userId: String?,
        @Path("studyId") studyId: String,
        @Path("instanceId") instanceId: String,
        @Body body: UpdateAnswersBody
    ): Deferred<UpdateAnswersResponseRemote>

    @PUT("/pepper/v1/user/{userId}/studies/{studyId}/activities/{instanceId}/answers")
    fun completeTaskAsync(
        @Path("userId") userId: String?,
        @Path("studyId") studyId: String,
        @Path("instanceId") instanceId: String
    ): Deferred<CompleteTaskResponseRemote>

    @GET("/pepper/v1/user/{userId}/participants")
    fun getParticipantsAsync(
        @Path("userId") userId: String?
    ): Deferred<GetParticipantsResponse>

    @POST("/pepper/v1/user/{userId}/participants")
    fun createParticipantAsync(
        @Path("userId") userId: String?,
        @Body body: CreateParticipantBody
    ): Deferred<CreateParticipantResponse>

    @GET("/pepper/v1/autocomplete/institution")
    fun getInstitutionAutocompleteAsync(
        @Query("namePattern") namePattern: String
    ): Deferred<List<InstitutionRemote>>

    @GET("/pepper/v1/studies")
    fun getStudiesAsync(
        @Query("umbrella") umbrella: String
    ): Deferred<List<StudyRemote>>

    @GET("/pepper/v1/studies/{studyId}")
    fun getStudyAsync(
        @Path("studyId") studyId: String
    ): Deferred<StudyRemote>

    @POST("/pepper/v1/mailing-list")
    fun addToMailingListAsync(
        @Body body: AddToMailingListBody
    ): Deferred<Unit>

    @GET("/pepper/v1/user/{userId}/profile/address")
    fun getMailingAddressesAsync(
        @Path("userId") userId: String?
    ): Deferred<List<MailingAddressRemote>>

    @GET("/pepper/v1/user/{userId}/profile/address/{addressId}")
    fun getMailingAddressAsync(
        @Path("userId") userId: String?,
        @Path("addressId") addressId: String?
    ): Deferred<MailingAddressRemote>

    @POST("/pepper/v1/user/{userId}/profile/address/verify")
    fun verifyMailingAddressAsync(
        @Path("userId") userId: String?,
        @Body body: MailingAddressRemote
    ): Deferred<MailingAddressRemote>

    @POST("/pepper/v1/user/{userId}/profile/address")
    fun createMailingAddressAsync(
        @Path("userId") userId: String?,
        @Query("strict") strict: Boolean,
        @Body body: MailingAddressRemote
    ): Deferred<MailingAddressRemote>

    @PUT("/pepper/v1/user/{userId}/profile/address/{addressId}")
    fun updateMailingAddressAsync(
        @Path("userId") userId: String?,
        @Path("addressId") addressId: String?,
        @Query("strict") strict: Boolean,
        @Body body: MailingAddressRemote
    ): Deferred<Unit>

    @DELETE("/pepper/v1/user/{userId}/profile/address/{addressId}")
    fun deleteMailingAddressAsync(
        @Path("userId") userId: String?,
        @Path("addressId") addressId: String?
    ): Deferred<Unit>

    @GET("/pepper/v1/cancers")
    fun getCancerTypesAsync(): Deferred<List<CancerTypeRemote>>

    @GET("/pepper/v1/user/{userId}/studies/{studyId}/status")
    fun getParticipantStatusAsync(
        @Path("userId") userId: String?,
        @Path("studyId") studyId: String
    ): Deferred<ParticipantStatusRemote>

    @GET("/pepper/v1/studies/{studyId}/password-requirements")
    fun getPasswordRequirementsAsync(
        @Path("studyId") studyId: String
    ): Deferred<PasswordRequirementsRemote>

    @PATCH("/pepper/v1/user/{userId}/email")
    fun updateEmailAsync(
        @Path("userId") userId: String?,
        @Body body: UpdateEmailBody
    ): Deferred<Unit>

    @PATCH("/pepper/v1/user/{userId}/password")
    fun updatePasswordAsync(
        @Path("userId") userId: String?,
        @Body body: UpdatePasswordBody
    ): Deferred<Unit>

    @GET("/pepper/v1/addresscountries")
    fun getCountriesAsync(): Deferred<List<CountryRemote>>

    @POST("/pepper/v1/temporary-users")
    fun createTemporaryUserAsync(
        @Body body: CreateTemporaryUserBody
    ): Deferred<CreateTemporaryUserResponse>

    @GET("/pepper/v1/studies/{studyId}/suggestions/drugs")
    fun getDrugSuggestionsAsync(
        @Path("studyId") studyId: String,
        @Query("q") query: String,
        @Query("limit") limit: Int
    ): Deferred<GetDrugSuggestionResponse>

    @POST("/pepper/v1/user/{userId}/studies/{studyId}/exit")
    fun withdrawFromStudyAsync(
        @Path("userId") userId: String?,
        @Path("studyId") studyId: String,
        @Body body: WithdrawFromStudyBody
    ): Deferred<Unit>
}