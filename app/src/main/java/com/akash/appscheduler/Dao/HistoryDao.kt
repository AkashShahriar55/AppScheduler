package com.akash.appscheduler.Dao

import androidx.room.*
import com.akash.appscheduler.Models.History
import kotlinx.coroutines.flow.Flow
import kotlin.math.min

@Dao
abstract class HistoryDao: BaseDao<History>() {


    @Query("select * from history_table")
    abstract fun getAllHistory(): Flow<List<History>>


    @Query("select * from history_table where id == :id")
    abstract fun getHistoryById(id:Int):Flow<History>



    @Query("select * from history_table join history_fts on history_table.app_name = history_fts.app_name where history_fts match :query ")
    abstract fun searchHistory(query: String): Flow<List<History>>

}