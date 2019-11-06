package org.broadinstitute.ddp.android.pepper.exposed.models

import org.junit.Assert.assertEquals
import org.junit.Test

class QuestionTest {

    @Test
    fun isRequired() {
        val rule = "rule"
        val message = "message"
        val validation = Validation.Required(rule, message)
        val validations = listOf(validation)
        val question = getQuestion(validations = validations)

        val expected = true
        val actual = question.isRequired()
        assertEquals(expected, actual)
    }

    @Test
    fun isNotRequired() {
        val validations = emptyList<Validation>()
        val question = getQuestion(validations = validations)

        val expected = false
        val actual = question.isRequired()
        assertEquals(expected, actual)
    }

    private fun getQuestion(
        id: String = "id",
        type: String = "type",
        prompt: String = "prompt",
        textPrompt: String = "textPrompt",
        additionalInfoHeader: String = "additionalInfoHeader",
        additionalInfoFooter: String = "additionalInfoFooter",
        answers: List<Answer> = emptyList(),
        validations: List<Validation> = emptyList()
    ): Question = Question.UnknownQuestion(
        id,
        type,
        prompt,
        textPrompt,
        additionalInfoHeader,
        additionalInfoFooter,
        answers,
        validations
    )
}