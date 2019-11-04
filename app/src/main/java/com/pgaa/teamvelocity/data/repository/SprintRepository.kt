package com.pgaa.teamvelocity.data.repository

import com.pgaa.teamvelocity.data.dao.SprintDao
import com.pgaa.teamvelocity.data.dao.StaffDao

class SprintRepository(private val sprintDao: SprintDao, private val staffDao: StaffDao) {
}