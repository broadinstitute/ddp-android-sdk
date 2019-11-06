package org.broadinstitute.ddp.android.pepper.internal.data.mailing

import org.broadinstitute.ddp.android.pepper.exposed.PepperAuthenticator
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
import org.broadinstitute.ddp.android.pepper.internal.common.tryForResult
import org.broadinstitute.ddp.android.pepper.internal.data.PepperService
import org.broadinstitute.ddp.android.pepper.internal.data.Result

internal class MailingRemoteDataSource(
    private val pepperService: PepperService,
    private val pepperAuthenticator: PepperAuthenticator
) : MailingDataSource {

    override suspend fun addToMailingList(request: AddToMailingListRequest): Result<Unit> =
        tryForResult {
            val studyId = request.studyId
            val firstName = request.firstName
            val lastName = request.lastName
            val email = request.email
            val body = AddToMailingListBody(studyId, firstName, lastName, email)

            pepperService.addToMailingListAsync(body)
                .await()
                .let { unit -> Result.Success(unit) }
        }

    override suspend fun getMailingAddresses(
        request: GetMailingAddressesRequest
    ): Result<List<MailingAddress>> = tryForResult {
        val userId = pepperAuthenticator.getUserId()

        pepperService.getMailingAddressesAsync(userId)
            .await()
            .mapNotNull { address ->
                when (val mapResult = address.mapToResult()) {
                    is Result.Success -> mapResult.value
                    is Result.Failure -> null
                }
            }
            .let { addresses -> Result.Success(addresses) }
    }

    override suspend fun getMailingAddress(request: GetMailingAddressRequest): Result<MailingAddress> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId()
            val addressId = request.addressId

            pepperService
                .getMailingAddressAsync(userId, addressId)
                .await()
                .mapToResult()
        }

    override suspend fun verifyMailingAddress(
        request: VerifyMailingAddressRequest
    ): Result<MailingAddress> = tryForResult {
        val userId = pepperAuthenticator.getUserId()
        val description: String = request.description
        val name: String = request.name
        val street1: String = request.street1
        val street2: String? = request.street2
        val city: String = request.city
        val state: String = request.state
        val country: String = request.country
        val zip: String = request.zip
        val phone: String = request.phone
        val isDefault: Boolean = request.isDefault

        val body = MailingAddressRemote(
            null,
            description,
            name,
            street1,
            street2,
            city,
            state,
            country,
            zip,
            phone,
            null,
            isDefault,
            null
        )

        pepperService
            .verifyMailingAddressAsync(userId, body)
            .await()
            .mapToResult()
    }

    override suspend fun createMailingAddress(
        request: CreateMailingAddressRequest
    ): Result<MailingAddress> = tryForResult {
        val userId = pepperAuthenticator.getUserId()
        val strict = request.strict
        val description: String = request.description
        val name: String = request.name
        val street1: String = request.street1
        val street2: String? = request.street2
        val city: String = request.city
        val state: String = request.state
        val country: String = request.country
        val zip: String = request.zip
        val phone: String = request.phone
        val isDefault: Boolean = request.isDefault

        val body = MailingAddressRemote(
            null,
            description,
            name,
            street1,
            street2,
            city,
            state,
            country,
            zip,
            phone,
            null,
            isDefault,
            null
        )

        pepperService
            .createMailingAddressAsync(userId, strict, body)
            .await()
            .mapToResult()
    }

    override suspend fun updateMailingAddress(
        request: UpdateMailingAddressRequest
    ): Result<Unit> = tryForResult {
        val userId = pepperAuthenticator.getUserId()
        val addressId = request.addressId
        val strict = request.strict
        val description: String = request.description
        val name: String = request.name
        val street1: String = request.street1
        val street2: String? = request.street2
        val city: String = request.city
        val state: String = request.state
        val country: String = request.country
        val zip: String = request.zip
        val phone: String = request.phone
        val isDefault: Boolean = request.isDefault

        val body = MailingAddressRemote(
            addressId,
            description,
            name,
            street1,
            street2,
            city,
            state,
            country,
            zip,
            phone,
            null,
            isDefault,
            null
        )

        pepperService
            .updateMailingAddressAsync(userId, addressId, strict, body)
            .await()
            .let { unit -> Result.Success(unit) }
    }

    override suspend fun deleteMailingAddress(request: DeleteMailingAddressRequest): Result<Unit> =
        tryForResult {
            val userId = pepperAuthenticator.getUserId()
            val addressId = request.addressId

            pepperService
                .deleteMailingAddressAsync(userId, addressId)
                .await()
                .let { unit -> Result.Success(unit) }
        }

    override suspend fun getCancerTypes(request: GetCancerTypesRequest): Result<List<CancerType>> =
        tryForResult {
            pepperService
                .getCancerTypesAsync()
                .await()
                .mapNotNull { cancerType ->
                    when (val mapResult = cancerType.mapToResult()) {
                        is Result.Success -> mapResult.value
                        is Result.Failure -> null
                    }
                }
                .let { cancerTypes -> Result.Success(cancerTypes) }
        }

    override suspend fun getCountries(request: GetCountriesRequest): Result<List<Country>> =
        tryForResult {
            pepperService
                .getCountriesAsync()
                .await()
                .mapNotNull { country ->
                    when (val mapResult = country.mapToResult()) {
                        is Result.Success -> mapResult.value
                        is Result.Failure -> null
                    }
                }
                .let { countries -> Result.Success(countries) }
        }
}