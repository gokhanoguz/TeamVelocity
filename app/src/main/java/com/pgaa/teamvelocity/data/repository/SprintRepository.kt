package com.pgaa.teamvelocity.data.repository

import com.pgaa.teamvelocity.data.dao.SprintDao
import com.pgaa.teamvelocity.data.dao.StaffDao
import com.pgaa.teamvelocity.data.entity.Sprint
import com.pgaa.teamvelocity.data.entity.Staff

class SprintRepository(private val sprintDao: SprintDao, private val staffDao: StaffDao) {

    suspend fun getAllSprints() = sprintDao.getAllSprint()
    suspend fun insertSprint(sprint: Sprint) = sprintDao.insert(sprint)
    suspend fun deleteSprint(sprint: Sprint) = sprintDao.deleteSprint(sprint)
    suspend fun getSprintByName(name: String) = sprintDao.getSprintByName(name)

    suspend fun getAllStaff() = staffDao.getAllStaff()
    suspend fun getStaffByName(name: String) = staffDao.getStaffByName(name)
    suspend fun insertStaff(staff: Staff) = staffDao.insert(staff)
    suspend fun deleteStaff(staff: Staff) = staffDao.deleteStaff(staff)
}