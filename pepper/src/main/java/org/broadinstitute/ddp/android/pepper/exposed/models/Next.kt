package org.broadinstitute.ddp.android.pepper.exposed.models

import org.broadinstitute.ddp.android.pepper.internal.common.Constants

enum class Next(val constant: String) {

    Task(Constants.NEXT_TASK),
    Dashboard(Constants.NEXT_DASHBOARD),
    Done(Constants.NEXT_DONE),
    ThankYou(Constants.NEXT_THANK_YOU),
    InternationalPatients(Constants.NEXT_INTERNATIONAL_PATIENTS),
    MailingList(Constants.NEXT_MAILING_LIST),
    Unknown(Constants.NEXT_UNKNOWN);

    companion object {
        fun fromString(constant: String?): Next =
            values().find { value -> value.constant == constant } ?: Unknown
    }
}