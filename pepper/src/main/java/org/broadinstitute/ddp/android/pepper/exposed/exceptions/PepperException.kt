package org.broadinstitute.ddp.android.pepper.exposed.exceptions

import java.io.IOException

abstract class PepperException(message: String = "") : IOException(message)