package com.github.pwoicik.uekschedule.feature_schedule.domain.use_case

import com.github.pwoicik.uekschedule.feature_schedule.data.db.entity.Group
import com.github.pwoicik.uekschedule.feature_schedule.domain.repository.ScheduleRepository

class DeleteGroup(
    private val repository: ScheduleRepository
) {

    suspend operator fun invoke(group: Group) {
        repository.deleteGroup(group)
    }
}
