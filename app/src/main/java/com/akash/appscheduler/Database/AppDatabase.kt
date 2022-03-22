package com.akash.appscheduler.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.akash.appscheduler.Converters.Converters
import com.akash.appscheduler.Dao.HistoryDao
import com.akash.appscheduler.Models.History
import com.akash.appscheduler.Models.HistoryFts


@Database(
    entities = [
        History::class,
        HistoryFts::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val historyDao:HistoryDao

    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null)
                {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_scheduler_database"
                    )
                        .fallbackToDestructiveMigration() // if migrate the data will be lost . need to implement differently
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }

    }


}