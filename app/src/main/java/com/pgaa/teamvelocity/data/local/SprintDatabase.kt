package com.pgaa.teamvelocity.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pgaa.teamvelocity.data.dao.SprintDao
import com.pgaa.teamvelocity.data.entity.Sprint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Sprint::class], version = 2)
abstract class SprintDatabase : RoomDatabase() {

    abstract fun sprintDao(): SprintDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SprintDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): SprintDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SprintDatabase::class.java,
                    "sprint_database"
                ).addCallback(SprintDatabaseCallback(scope))
                 .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class SprintDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var dao = database.sprintDao()

                    var sprint = Sprint("Q4Sprint2", "4", "", 50.0, 35.5, 1.40)
                    dao.insert(sprint)

                    sprint = Sprint("Q4Sprint1", "4", "", 71.0, 48.0, 1.47)
                    dao.insert(sprint)

                    sprint = Sprint("Q3Sprint7", "3", "", 71.0, 49.5, 1.43)
                    dao.insert(sprint)

                    sprint = Sprint("Q3Sprint6", "3", "", 37.0, 35.5, 1.04)
                    dao.insert(sprint)

                    sprint = Sprint("Q3Sprint5", "3", "", 55.0, 42.0, 1.30)
                    dao.insert(sprint)

                }
            }
        }
    }
}