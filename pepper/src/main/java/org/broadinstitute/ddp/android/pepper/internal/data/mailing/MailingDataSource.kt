package org.broadinstitute.ddp.android.pepper.internal.data.mailing

import org.broadinstitute.ddp.android.pepper.exposed.models.CancerType
import org.broadinstitute.ddp.android.pepper.exposed.models.Country
import org.broadinstitute.ddp.android.pepper.exposed.models.MailingAddress
import org.broadinstitute.ddp.android.pepper.exposed.requests.mailing.AddToMailingListRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.mailing.CreateMailingAddressRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.mailing.DeleteMailingAddressRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.mailing.GetCancerTypesRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.mailing.GetCountriesRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.mailing.GetMailingAddressRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.mailing.GetMailingAddressesRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.mailing.UpdateMailingAddressRequest
import org.broadinstitute.ddp.android.pepper.exposed.requests.mailing.VerifyMailingAddressRequest
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal interface MailingDataSource {

    suspend fun addToMailingList(request: AddToMailingListRequest): Result<Unit>

    suspend fun getMailingAddresses(request: GetMailingAddressesRequest): Result<List<MailingAddress>>

    suspend fun getMailingAddress(request: GetMailingAddressRequest): Result<MailingAddress>

    suspend fun verifyMailingAddress(request: VerifyMailingAddressRequest): Result<MailingAddress>

    suspend fun createMailingAddress(request: CreateMailingAddressRequest): Result<MailingAddress>

    suspend fun updateMailingAddress(request: UpdateMailingAddressRequest): Result<Unit>

    suspend fun deleteMailingAddress(request: DeleteMailingAddressRequest): Result<Unit>

    suspend fun getCancerTypes(request: GetCancerTypesRequest): Result<List<CancerType>>

    suspend fun getCountries(request: GetCountriesRequest): Result<List<Country>>
}