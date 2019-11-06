package org.broadinstitute.ddp.android.pepper.internal.data.user

import org.broadinstitute.ddp.android.pepper.exposed.PepperAuthenticator
import org.broadinstitute.ddp.android.pepper.exposed.models.Participant
import org.broadinstitute.ddp.android.pepper.exposed.models.PasswordRequirements
import org.broadinstitute.ddp.android.pepper.exposed.models.TemporaryUser
import org.broadinstitute.ddp.android.pepper.exposed.models.UserProfile
import org.broadinstitute.ddp.android.pepper.exposed.requests.user.CreateParticipantRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.user.CreateTemporaryUserRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.user.GetParticipantsRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.user.GetPasswordRequirementsRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.user.GetUserProfileRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.user.UpdateEmailRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.user.UpdatePasswordRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.user.UpdateUserProfileRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.user.WithdrawFromStudyRequest
import org.broadinstitute.ddp.android.pepper.internal.common.tryForResult
import org.broadinstitute.ddp.android.pepper.internal.data.PepperService
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal class UserRemoteDataSource(
    private val pepperService: PepperService,
    private val pepperAuthenticator: PepperAuthenticator
) : UserDataSource {

    override suspend fun getUserProfile(request: GetUserProfileRequest): Result<UserProfile> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId()

            pepperService.getUserProfileAsync(userId)
                .await()
                .mapToResult()
        }

    override suspend fun updateUserProfile(request: UpdateUserProfileRequest): Result<UserProfile> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId()
            val userProfile = request.userProfile
            val userProfileRemote = UserProfileRemote(
                userProfile.birthMonth,
                userProfile.birthYear,
                userProfile.birthDayInMonth,
                userProfile.sex?.constant,
                userProfile.preferredLanguage,
                userProfile.firstName,
                userProfile.lastName
            )

            pepperService.updateUserProfileAsync(userId, userProfileRemote)
                .await()
                .mapToResult()
        }

    override suspend fun getParticipants(request: GetParticipantsRequest): Result<List<Participant>> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId()

            pepperService.getParticipantsAsync(userId)
                .await()
                .mapToResult()
        }

    override suspend fun createParticipant(request: CreateParticipantRequest): Result<Participant> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId()
            val alias = request.alias
            val body = CreateParticipantBody(alias)

            val result: Result<Participant> =
                when (val mapResult =
                    pepperService.createParticipantAsync(userId, body).await().mapToResult()) {
                    is Result.Success -> Result.Success(Participant(mapResult.value, alias))
                    is Result.Failure -> Result.Failure(mapResult.error)
                }

            result
        }

    override suspend fun getPasswordRequirements(request: GetPasswordRequirementsRequest): Result<PasswordRequirements> =
        tryForResult {
            val studyId = request.studyId

            pepperService.getPasswordRequirementsAsync(studyId)
                .await()
                .mapToResult()
        }

    override suspend fun updateEmail(request: UpdateEmailRequest): Result<Unit> = tryForResult {
        val userId = pepperAuthenticator.getUserId()
        val email = request.email
        val body = UpdateEmailBody(email)

        pepperService.updateEmailAsync(userId, body)
            .await()
            .let { unit -> Result.Success(unit) }
    }

    override suspend fun updatePassword(request: UpdatePasswordRequest): Result<Unit> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId()
            val currentPassword = request.currentPassword
            val password = request.password
            val body = UpdatePasswordBody(currentPassword, password)

            pepperService.updatePasswordAsync(userId, body)
                .await()
                .let { unit -> Result.Success(unit) }
        }

    override suspend fun createTemporaryUser(request: CreateTemporaryUserRequest): Result<TemporaryUser> =
        tryForResult {
            val auth0ClientId = request.auth0ClientId
            val body = CreateTemporaryUserBody(auth0ClientId)

            pepperService.createTemporaryUserAsync(body)
                .await()
                .mapToResult()
        }

    override suspend fun withdrawFromStudy(request: WithdrawFromStudyRequest): Result<Unit> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId()
            val studyId = request.studyId
            val body = WithdrawFromStudyBody(request.notes)

            pepperService.withdrawFromStudyAsync(userId, studyId, body)
                .await()
                .let { unit -> Result.Success(unit) }
        }
}