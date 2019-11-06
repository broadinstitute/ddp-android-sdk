package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.Locale

@Parcelize
data class Date(
    val year: Int?,
    val month: Int?,
    val day: Int?
) : Parcelable {

    fun toFormattedString(locale: Locale = Locale.getDefault()): String {

        val pattern = when {
            year != null && month != null && day != null -> "MMMM dd, yyyy"
            year != null && month != null -> "MMMM yyyy"
            year != null -> "yyyy"
            month != null -> "MMMM"
            day != null -> "dd"
            else -> null
        }

        val simpleDateFormat = if (pattern != null) SimpleDateFormat(pattern, locale) else null

        val calendar = GregorianCalendar()
        if (year != null) calendar.set(Calendar.YEAR, year)
        if (month != null) calendar.set(Calendar.MONTH, month)
        if (day != null) calendar.set(Calendar.DAY_OF_MONTH, day)

        return simpleDateFormat?.format(calendar.time) ?: ""
    }
}