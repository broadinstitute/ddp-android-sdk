package org.broadinstitute.ddp.android.pepper.internal.common.extensions

import com.auth0.android.jwt.JWT

internal fun JWT.authorization() = "Bearer $this"

internal fun JWT.userId(): String? =
    this.getClaim("https://datadonationplatform.org/uid").asString()
