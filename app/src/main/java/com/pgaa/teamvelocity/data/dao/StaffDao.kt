package com.pgaa.teamvelocity.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.pgaa.teamvelocity.data.entity.Staff

@Dao
interface StaffDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(staff: Staff)

}