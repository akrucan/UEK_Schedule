package com.github.pwoicik.uekschedule.feature_schedule.presentation.screens.createActivity

import com.github.pwoicik.uekschedule.feature_schedule.data.db.entity.Activity
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

data class CreateActivityState(

    val name: String = "",
    val location: String = "",
    val type: String = "",
    val teacher: String = "",
    val startTime: LocalTime? = null,
    val durationMinutes: String = "90",
    val startDate: LocalDate? = null,
    val repeatActivity: Boolean = false,
    val repeatOnDaysOfWeek: Set<DayOfWeek> = emptySet()
) {
    constructor(activity: Activity) : this(
        name = activity.name,
        location = activity.location ?: "",
        type = activity.type ?: "",
        teacher = activity.teacher ?: "",
        startDate = if (activity.isOneshot) activity.startDateTime.toLocalDate() else null,
        startTime = activity.startDateTime.toLocalTime(),
        durationMinutes = activity.durationMinutes.toString(),
        repeatActivity = !activity.isOneshot,
        repeatOnDaysOfWeek = activity.repeatOnDaysOfWeek?.toSet() ?: emptySet()
    )

    val isInputValid: Boolean
        get() {
            val isNameValid = name.isNotBlank()
            val isStartTimeValid = startTime != null
            val isStartDateValid = if (repeatActivity) true else startDate != null
            val isRepeatValid = if (repeatActivity) repeatOnDaysOfWeek.isNotEmpty() else true
            return isNameValid && isStartTimeValid && isStartDateValid && isRepeatValid
        }
}
