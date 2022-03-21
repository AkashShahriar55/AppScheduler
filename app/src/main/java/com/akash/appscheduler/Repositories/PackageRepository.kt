package com.akash.appscheduler.Repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akash.appscheduler.Models.PackageInfo
import com.akash.appscheduler.Repositories.DataSource.PackageDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PackageRepository(
    val packageDataSource: PackageDataSource
) : CoroutineScope{
    private val packageInfo = MutableLiveData<List<PackageInfo>>()
    private val _packageInfo:LiveData<List<PackageInfo>> = packageInfo
    fun fetchPackageData(context: Context): LiveData<List<PackageInfo>>{
        launch {
            packageInfo.value = packageDataSource.fetchPackageInfo(context)
        }
        return _packageInfo
    }


    override val coroutineContext: CoroutineContext = Job()


}