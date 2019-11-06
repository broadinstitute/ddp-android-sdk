package org.broadinstitute.ddp.android.pepper.exposed.models

import org.broadinstitute.ddp.android.pepper.internal.common.Constants

enum class UserSex(val constant: String) {

    Male(Constants.USER_SEX_MALE),
    Female(Constants.USER_SEX_FEMALE),
    InterSex(Constants.USER_SEX_INTER_SEX),
    PreferNotToAnswer(Constants.USER_SEX_PREFER_NOT_TO_ANSWER);

    companion object {
        fun fromString(constant: String?): UserSex? =
            values().find { value -> value.constant == constant }
    }
}