package com.akash.appscheduler.Repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.akash.appscheduler.Models.PackageInfo
import com.akash.appscheduler.Modules.PackageManagerDataSource
import com.akash.appscheduler.Repositories.DataSource.PackageDataSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class  PackageRepository @Inject constructor(
) : CoroutineScope{




    @PackageManagerDataSource
    @Inject
    lateinit var packageDataSource: PackageDataSource
    suspend fun fetchPackageData(context: Context){
        packageDataSource.fetchPackageInfo(context)
    }

    fun getPackageInfo(): LiveData<List<PackageInfo>> {
        return packageDataSource.getPackageInfo()
    }


    override val coroutineContext: CoroutineContext = Job()


}