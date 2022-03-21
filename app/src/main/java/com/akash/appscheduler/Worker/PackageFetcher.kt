package com.akash.appscheduler.Worker

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class PackageFetcher(
    context: Context,
    params: WorkerParameters
): CoroutineWorker(context,params) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {





            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }


    companion object {
        private const val TAG = "PrepopulateDbWorker"
        const val KEY_FILENAME = "UNIT_DATA_FILENAME"
    }
}