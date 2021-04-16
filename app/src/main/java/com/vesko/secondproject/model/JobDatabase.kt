package com.vesko.secondproject.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database ( entities = [JobPojo::class], version = 1, exportSchema = false)
abstract class JobDatabase : RoomDatabase() {
    abstract fun jobDao(): JobDao

    companion object {
        @Volatile
        private var INSTANCE: JobDatabase? = null

        fun getDatabase(context: Context) : JobDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance
            else
                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        JobDatabase::class.java,
                        "job_database"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
        }
    }
}