package com.github.pwoicik.uekschedule.feature_schedule.domain.use_case

import com.github.pwoicik.uekschedule.feature_schedule.data.db.entity.Activity
import com.github.pwoicik.uekschedule.feature_schedule.domain.repository.ScheduleRepository

class DeleteActivity(
    private val repository: ScheduleRepository
) {

    suspend operator fun invoke(activity: Activity) {
        repository.deleteActivity(activity)
    }
}
