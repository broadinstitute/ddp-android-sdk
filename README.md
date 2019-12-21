DDP Android SDK
=====================

Architecture
------------
The app is sectioned into two main packages: `exposed` and `internal`.

`PepperClient` can execute any `PepperRequest` when passed to the `request` function. When `request` is called a `PepperCall` instance is returned. With a `PepperCall`, you can choose to execute on current thread or use a `PepperCallback` to handle the async threading. Once the request is completed, a `PepperResponse` will be returned with either a `Success` or `Failure`.
`PepperClient` depends on a `PepperAuthenticator` object to retrieve or refresh a token.

DataSources handle the network calls internally and map the Remote models to exposed models.

Core Libraries
--------------
- Threading with [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- Inversion of control with [Koin](https://github.com/InsertKoinIO/koin)
- HTTP client with [OkHttp](https://github.com/square/okhttp)
- REST client with [Retrofit](https://square.github.io/retrofit/)

Assembling SDK
--------------

### AAR
The aar file can be shared to utilize this SDK in other Android apps.
To assemble the AAR file run `./gradlew assemble` in the root folder of the SDK.
Once the assemble has completed, the AAR file can be found in `/repo.../pepper/build/outputs/aar/`

Publishing SDK
-------------
In the future when this SDK is open source, I'd recommend the SDK to be published.
Here is a great [tutorial](https://proandroiddev.com/creating-and-publishing-an-android-library-402976b5d9d2) on how to publish an android library.
