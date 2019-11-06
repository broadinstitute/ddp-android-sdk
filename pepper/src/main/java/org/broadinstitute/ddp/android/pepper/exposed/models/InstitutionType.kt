package org.broadinstitute.ddp.android.pepper.exposed.models

import org.broadinstitute.ddp.android.pepper.internal.common.Constants

enum class InstitutionType(val constant: String, val path: String) {

    Institution(
        Constants.INSTITUTION_TYPE_INSTITUTION,
        Constants.INSTITUTION_TYPE_PATH_INSTITUTION
    ),
    InitialBiopsy(
        Constants.INSTITUTION_TYPE_INITIAL_BIOPSY,
        Constants.INSTITUTION_TYPE_PATH_INITIAL_BIOPSY
    ),
    Physician(Constants.INSTITUTION_TYPE_PHYSICIAN, Constants.INSTITUTION_TYPE_PATH_PHYSICIAN);

    companion object {
        fun fromString(constant: String?): InstitutionType? =
            values().find { value -> value.constant == constant }
    }
}