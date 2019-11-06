package org.broadinstitute.ddp.android.pepper.internal.common.modules

import android.content.Context
import okhttp3.OkHttpClient
import org.broadinstitute.ddp.android.pepper.exposed.PepperAuthenticator
import org.broadinstitute.ddp.android.pepper.internal.common.okhttp.ConnectivityInterceptor
import org.broadinstitute.ddp.android.pepper.internal.common.okhttp.TokenAuthenticator
import org.broadinstitute.ddp.android.pepper.internal.common.okhttp.TokenInterceptor
import org.broadinstitute.ddp.android.pepper.internal.data.DeferredCallAdapterFactory
import org.broadinstitute.ddp.android.pepper.internal.data.PepperService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val CONTEXT = "CONTEXT"
const val BASE_URL = "BASE_URL"

internal val remoteDataModule = module {
    single {
        createOkHttpClient(getProperty(CONTEXT), getProperty(PEPPER_AUTHENTICATOR))
    }

    single { createRetrofit(getProperty(BASE_URL), get()) }
    single { get<Retrofit>().create(PepperService::class.java) }
}

private fun createOkHttpClient(
    context: Context,
    pepperAuthenticator: PepperAuthenticator
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(ConnectivityInterceptor(context))
        .addInterceptor(TokenInterceptor(pepperAuthenticator))
        .authenticator(TokenAuthenticator(pepperAuthenticator))
        .build()

private fun createRetrofit(baseUrl: String, client: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(client)
    .addCallAdapterFactory(DeferredCallAdapterFactory.invoke())
    .addConverterFactory(GsonConverterFactory.create())
    .build()
