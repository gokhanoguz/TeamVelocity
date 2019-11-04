package com.pgaa.teamvelocity.data.dao

import androidx.lifecycle.LiveData
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
    suspend fun deleteSprint(vararg sprint: Sprint)

    @Query("SELECT * from sprint_table ORDER BY name ASC")
    suspend fun getAllSprint(): LiveData<List<Sprint>>

    @Query("SELECT * from sprint_table WHERE name LIKE :name")
    suspend fun getSprintByName(name: String)

}