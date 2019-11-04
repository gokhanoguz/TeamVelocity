package com.pgaa.teamvelocity.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pgaa.teamvelocity.data.entity.Staff

@Dao
interface StaffDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(staff: Staff)

    @Delete
    suspend fun deleteStaff(vararg staff: Staff)

    @Query("SELECT * from staff_table ORDER BY name ASC")
    suspend fun getAllStaff(): List<Staff>

    @Query("SELECT * from staff_table WHERE name LIKE :name")
    suspend fun getStaffByName(name: String)

}