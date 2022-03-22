package com.akash.appscheduler.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import java.util.*


@Entity(
    tableName = "history_table"
)
data class History(
    @ColumnInfo(name = "app_name")
    val appName:String,
    @ColumnInfo(name = "package_name")
    val packageName:String,
    @ColumnInfo(name = "schedule_time")
    val scheduleTime:Calendar = Calendar.getInstance(),
    val started:Boolean
){
    @PrimaryKey(autoGenerate = true) var id:Long = 0
}


@Entity(tableName = "history_fts")
@Fts4(contentEntity = History::class)
data class HistoryFts(
    @ColumnInfo(name = "app_name")
    val name: String
)
