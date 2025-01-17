package com.github.pwoicik.uekschedule.feature_schedule.data.util

import androidx.room.TypeConverter
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

object ScheduleDbTypeConverters {
    @TypeConverter
    @JvmStatic
    fun zonedDateTimeFromTimestamp(timestamp: Long): ZonedDateTime {
        val instant = Instant.ofEpochSecond(timestamp)
        return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault())
    }

    @TypeConverter
    @JvmStatic
    fun zonedDateTimeToTimestamp(dateTime: ZonedDateTime): Long = dateTime.toEpochSecond()

    @TypeConverter
    @JvmStatic
    fun dayOfWeekListFromString(string: String?): List<DayOfWeek>? =
        string?.split(',')?.map { DayOfWeek.of(it.toInt()) }

    @TypeConverter
    @JvmStatic
    fun dayOfWeekListToString(list: List<DayOfWeek>?): String? =
        list?.joinToString(",") { it.value.toString() }

    @TypeConverter
    @JvmStatic
    fun stringListToString(list: List<String>?): String? =
        list?.joinToString(",")

    @TypeConverter
    @JvmStatic
    fun stringListFromString(string: String?): List<String>? =
        string?.split(',')

}
