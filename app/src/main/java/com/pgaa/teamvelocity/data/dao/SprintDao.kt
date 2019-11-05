package com.pgaa.teamvelocity.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pgaa.teamvelocity.data.entity.Sprint

@Dao
interface SprintDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sprint: Sprint)

    @Delete
    suspend fun deleteSprint(sprint: Sprint)

    @Query("SELECT * from sprint_table ORDER BY name DESC")
    suspend fun getAllSprint(): List<Sprint>

    @Query("SELECT * from sprint_table WHERE name LIKE :name LIMIT 1")
    suspend fun getSprintByName(name: String): Sprint

    @Query("SELECT * from sprint_table WHERE quarter LIKE :quarter")
    suspend fun getSprintfByQuarter(quarter: String): List<Sprint>

}