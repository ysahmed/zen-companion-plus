package com.waesh.mindfulbell.model.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.waesh.mindfulbell.model.entity.Reminder

@androidx.room.Database(entities = [Reminder::class], version = 1)
abstract class Database: RoomDatabase() {

    abstract fun getDao(): RemindersDao

    companion object{
        @Volatile
        private var INSTANCE: Database? = null
        fun getDatabase(context: Context): Database {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "database"
                )
                    .createFromAsset("database.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}