package org.broadinstitute.ddp.android.pepper.exposed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TaskInstance(
    val id: String,
    val name: String,
    val status: String,
    val taskType: TaskType?,
    val formType: FormType?,
    val listStyleHint: ListStyleHint?,
    val readOnly: Boolean,
    val sections: List<Section>,
    val introduction: Section?,
    val closing: Section?,
    val subTitle: String?,
    val lastUpdatedText: String?,
    val readOnlyHint: String?
) : Parcelable {

    fun getFlattenedSteps(): List<Step> {
        val introSteps = introduction?.steps ?: emptyList()

        val sectionSteps: List<Step> = sections.fold(emptyList()) { steps, section ->
            steps + section.steps
        }

        val closingSteps = closing?.steps ?: emptyList()

        return introSteps + sectionSteps + closingSteps
    }
}