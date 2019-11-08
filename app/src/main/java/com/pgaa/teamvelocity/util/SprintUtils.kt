package com.pgaa.teamvelocity.util

import com.pgaa.teamvelocity.data.entity.Sprint
import com.pgaa.teamvelocity.data.model.SprintStats

object SprintUtils {
    fun calculateSprintStats(sprintList: List<Sprint>?) : SprintStats {
        var avRatio = 0.0
        var avStoryPoint = 0.0
        var avManDay = 0.0
        sprintList?.let {sprintList ->
            for (sprint in sprintList) {
                avStoryPoint += sprint.storyPoints
                avManDay += sprint.mandDay
            }
            avStoryPoint /= sprintList.size
            avManDay /= sprintList.size
            avRatio = avStoryPoint/avManDay
        }
        return SprintStats(avRatio, avStoryPoint, avManDay)
    }
}