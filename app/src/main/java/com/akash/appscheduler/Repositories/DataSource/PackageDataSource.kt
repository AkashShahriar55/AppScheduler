package com.akash.appscheduler.Repositories.DataSource

import android.content.Context
import androidx.lifecycle.LiveData
import com.akash.appscheduler.Models.PackageInfo
import kotlinx.coroutines.flow.Flow

interface PackageDataSource {
    suspend fun fetchPackageInfo(context: Context):List<PackageInfo>
}