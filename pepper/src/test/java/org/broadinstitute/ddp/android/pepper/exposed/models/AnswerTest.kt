package org.broadinstitute.ddp.android.pepper.exposed.models

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AnswerTest {

    @Test
    fun isValidBooleanAnswerTrue1() {
        val boolean = false
        val answer = Answer.BooleanAnswer("", "", boolean)

        val validations = listOf(
            Validation.Required("", "")
        )

        val condition = answer.isValid(validations)
        assertTrue(condition)
    }

    @Test
    fun isValidDateAnswerDateRangeTrue4() {
        val year = 1996
        val month = 10
        val day = 21
        val date = Date(year, month, day)
        val answer = Answer.DateAnswer("", "", date)

        val startDate = "1996-11-21"
        val endDate = "2000-1-1"
        val validations = listOf(
            Validation.DateRange("", "", startDate, endDate),
            Validation.Required("", "")
        )

        val condition = answer.isValid(validations)
        assertTrue(condition)
    }

    @Test
    fun isValidDateAnswerDateRangeTrue5() {
        val year = 2000
        val month = 0
        val day = 1
        val date = Date(year, month, day)
        val answer = Answer.DateAnswer("", "", date)

        val startDate = "1996-11-21"
        val endDate = "2000-1-1"
        val validations = listOf(
            Validation.DateRange("", "", startDate, endDate),
            Validation.Required("", "")
        )

        val condition = answer.isValid(validations)
        assertTrue(condition)
    }

    @Test
    fun isValidDateAnswerDateRangeFalse4() {
        val year = 1996
        val month = 10
        val day = 20
        val date = Date(year, month, day)
        val answer = Answer.DateAnswer("", "", date)

        val startDate = "1996-11-21"
        val endDate = "2000-1-1"
        val validations = listOf(
            Validation.DateRange("", "", startDate, endDate),
            Validation.Required("", "")
        )

        val condition = answer.isValid(validations)
        assertFalse(condition)
    }

    @Test
    fun isValidDateAnswerDateRangeFalse5() {
        val year = 2000
        val month = 0
        val day = 2
        val date = Date(year, month, day)
        val answer = Answer.DateAnswer("", "", date)

        val startDate = "1996-11-21"
        val endDate = "2000-1-1"
        val validations = listOf(
            Validation.DateRange("", "", startDate, endDate),
            Validation.Required("", "")
        )

        val condition = answer.isValid(validations)
        assertFalse(condition)
    }

    @Test
    fun isValidCompositeAnswerTrue1() {
        val text = "text"
        val textAnswer = Answer.TextAnswer("", "", text)

        val year = 1996
        val month = 10
        val day = 21
        val date = Date(year, month, day)
        val dateAnswer = Answer.DateAnswer("", "", date)

        val answers = listOf(listOf(dateAnswer, textAnswer))
        val answer = Answer.CompositeAnswer("", "", answers)

        val validations = listOf(
            Validation.Required("", "")
        )

        val condition = answer.isValid(validations)
        assertTrue(condition)
    }

    @Test
    fun isValidUnknownAnswerTrue1() {
        val answer = Answer.UnknownAnswer("", "", null)

        val validations = listOf(
            Validation.Required("", "")
        )

        val condition = answer.isValid(validations)
        assertTrue(condition)
    }
}