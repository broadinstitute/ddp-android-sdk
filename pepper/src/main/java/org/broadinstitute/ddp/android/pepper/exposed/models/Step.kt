package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Step : Parcelable {

    abstract val id: String?
    abstract val type: String?
    abstract val show: Boolean

    abstract fun updateShow(show: Boolean): Step

    @Parcelize
    data class ComponentStep(
        override val id: String,
        override val type: String,
        override val show: Boolean,
        val component: Component
    ) : Step() {
        override fun updateShow(show: Boolean): Step = this.copy(show = show)
    }

    @Parcelize
    data class QuestionStep(
        override val id: String,
        override val type: String,
        override val show: Boolean,
        val question: Question
    ) : Step() {
        fun updateAnswers(answers: List<Answer>) =
            this.copy(question = question.updateAnswers(answers))

        override fun updateShow(show: Boolean): Step = this.copy(show = show)
    }

    @Parcelize
    data class ContentStep(
        override val id: String,
        override val type: String,
        override val show: Boolean,
        val title: String?,
        val body: String?
    ) : Step() {
        override fun updateShow(show: Boolean): Step = this.copy(show = show)
    }

    @Parcelize
    data class GroupedContentStep(
        override val id: String?,
        override val type: String?,
        override val show: Boolean,
        val contentSteps: List<ContentStep>,
        val title: String?
    ) : Step() {
        override fun updateShow(show: Boolean): Step = this.copy(show = show)
    }

    @Parcelize
    data class UnknownStep(
        override val id: String?,
        override val type: String?,
        override val show: Boolean
    ) : Step() {
        override fun updateShow(show: Boolean): Step = this.copy(show = show)
    }
}