package com.pgaa.teamvelocity.data.repository

import com.pgaa.teamvelocity.data.dao.SprintDao
import com.pgaa.teamvelocity.data.entity.Sprint

class SprintRepository(private val sprintDao: SprintDao) {

    suspend fun getAllSprints() = sprintDao.getAllSprint()
    suspend fun insertSprint(sprint: Sprint) = sprintDao.insert(sprint)
    suspend fun deleteSprint(sprint: Sprint) = sprintDao.deleteSprint(sprint)
    suspend fun getSprintByName(name: String) = sprintDao.getSprintByName(name)
}