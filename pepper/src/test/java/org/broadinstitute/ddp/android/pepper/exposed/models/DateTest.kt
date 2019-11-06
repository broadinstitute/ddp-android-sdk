package org.broadinstitute.ddp.android.pepper.exposed.models

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Locale

class DateTest {

    @Test
    fun toFormattedStringFullDateJan() {
        val year = 1996
        val month = 0
        val day = 21
        val date = Date(year, month, day)
        val locale = Locale.US

        val expected = "January 21, 1996"
        val actual = date.toFormattedString(locale)

        assertEquals(expected, actual)
    }

    @Test
    fun toFormattedStringFullDateDec() {
        val year = 1996
        val month = 11
        val day = 21
        val date = Date(year, month, day)
        val locale = Locale.US

        val expected = "December 21, 1996"
        val actual = date.toFormattedString(locale)

        assertEquals(expected, actual)
    }

    @Test
    fun toFormattedStringMonthYearJan() {
        val year = 1996
        val month = 0
        val day = null
        val date = Date(year, month, day)
        val locale = Locale.US

        val expected = "January 1996"
        val actual = date.toFormattedString(locale)

        assertEquals(expected, actual)
    }

    @Test
    fun toFormattedStringMonthYearDec() {
        val year = 1996
        val month = 11
        val day = null
        val date = Date(year, month, day)
        val locale = Locale.US

        val expected = "December 1996"
        val actual = date.toFormattedString(locale)

        assertEquals(expected, actual)
    }

    @Test
    fun toFormattedStringYear() {
        val year = 1996
        val month = null
        val day = null
        val date = Date(year, month, day)
        val locale = Locale.US

        val expected = "1996"
        val actual = date.toFormattedString(locale)

        assertEquals(expected, actual)
    }

    @Test
    fun toFormattedStringMonthJan() {
        val year = null
        val month = 0
        val day = null
        val date = Date(year, month, day)
        val locale = Locale.US

        val expected = "January"
        val actual = date.toFormattedString(locale)

        assertEquals(expected, actual)
    }

    @Test
    fun toFormattedStringMonthDec() {
        val year = null
        val month = 11
        val day = null
        val date = Date(year, month, day)
        val locale = Locale.US

        val expected = "December"
        val actual = date.toFormattedString(locale)

        assertEquals(expected, actual)
    }

    @Test
    fun toFormattedStringDay() {
        val year = null
        val month = null
        val day = 1
        val date = Date(year, month, day)
        val locale = Locale.US

        val expected = "01"
        val actual = date.toFormattedString(locale)

        assertEquals(expected, actual)
    }
}