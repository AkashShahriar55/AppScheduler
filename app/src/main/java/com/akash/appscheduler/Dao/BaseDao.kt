package com.akash.appscheduler.Dao

import android.util.Log
import androidx.room.*

@Dao
abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(obj:T):Long // returns the row

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(obj:List<T>):List<Long>


    @Update
    abstract fun update(obj:T):Int

    @Update
    abstract fun update(obj:List<T>):Int

    @Delete
    abstract fun delete(obj:T):Int

    @Delete
    abstract fun delete(obj:List<T>):Int


    @Transaction
    open fun upsert(obj:T): Long {
        val row = insert(obj)
        Log.d("result", "upsert: $row")
        if(row == -1L){
            return update(obj).toLong()
        }
        return row
    }

    @Transaction
    open fun upsert(obj:List<T>){
        val rows = insert(obj)
        val updateList = arrayListOf<T>()
        for(i in rows.indices){
            if(rows[i] == -1L){
                updateList += obj[i]
            }
        }

        if(updateList.isNotEmpty()){
            update(updateList)
        }
    }
}