package com.pgaa.teamvelocity.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pgaa.teamvelocity.data.dao.SprintDao
import com.pgaa.teamvelocity.data.entity.Sprint

@Database(entities = [Sprint::class], version = 1)
abstract class SprintDatabase : RoomDatabase() {
    
    abstract fun sprintDao() : SprintDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SprintDatabase? = null

        fun getDatabase(context: Context): SprintDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SprintDatabase::class.java,
                    "sprint_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}