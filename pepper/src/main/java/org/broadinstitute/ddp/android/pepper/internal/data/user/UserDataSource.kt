package org.broadinstitute.ddp.android.pepper.internal.data.user

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
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal interface UserDataSource {

    suspend fun getUserProfile(request: GetUserProfileRequest): Result<UserProfile>

    suspend fun updateUserProfile(request: UpdateUserProfileRequest): Result<UserProfile>

    suspend fun getParticipants(request: GetParticipantsRequest): Result<List<Participant>>

    suspend fun createParticipant(request: CreateParticipantRequest): Result<Participant>

    suspend fun getPasswordRequirements(request: GetPasswordRequirementsRequest): Result<PasswordRequirements>

    suspend fun updateEmail(request: UpdateEmailRequest): Result<Unit>

    suspend fun updatePassword(request: UpdatePasswordRequest): Result<Unit>

    suspend fun createTemporaryUser(request: CreateTemporaryUserRequest): Result<TemporaryUser>

    suspend fun withdrawFromStudy(request: WithdrawFromStudyRequest): Result<Unit>
}