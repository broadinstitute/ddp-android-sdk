package org.broadinstitute.ddp.android.pepper.internal.common.extensions

import android.content.Context
import android.net.ConnectivityManager

internal fun Context?.isNetworkAvailable(): Boolean {
    val connectivityManager = this?.getSystemService(Context.CONNECTIVITY_SERVICE)
        as? ConnectivityManager

    return connectivityManager?.activeNetworkInfo != null
}