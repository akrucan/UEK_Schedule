package com.github.pwoicik.uekschedule.feature_schedule.presentation.components.scheduleEntriesList

import com.github.pwoicik.uekschedule.feature_schedule.domain.model.ScheduleEntry
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.time.DurationUnit
import kotlin.time.toDuration

sealed class ScheduleEntryStatus {
    data class NotStarted(val minutesToStart: Long) : ScheduleEntryStatus()
    data class InProgress(val minutesRemaining: Long) : ScheduleEntryStatus()
    object Ended : ScheduleEntryStatus()
}

fun ScheduleEntry.status(timeNow: LocalDateTime): ScheduleEntryStatus {
    return when {
        timeNow.isBefore(startDateTime) -> ScheduleEntryStatus.NotStarted(
            timeNow.until(startDateTime, ChronoUnit.MINUTES)
        )
        timeNow.isBefore(endDateTime) -> ScheduleEntryStatus.InProgress(
            timeNow.until(endDateTime, ChronoUnit.MINUTES)
        )
        else -> ScheduleEntryStatus.Ended
    }
}
