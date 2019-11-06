package org.broadinstitute.ddp.android.pepper.exposed.exceptions

import kotlin.jvm.internal.ClassReference
import kotlin.reflect.KClass

class MalformedResponseException(kClass: KClass<*>) :
    PepperException((kClass as ClassReference).jClass.name)