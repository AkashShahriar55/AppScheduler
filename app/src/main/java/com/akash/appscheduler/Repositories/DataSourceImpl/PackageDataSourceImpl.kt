package com.akash.appscheduler.Repositories.DataSourceImpl

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akash.appscheduler.Models.PackageInfo
import com.akash.appscheduler.Repositories.DataSource.PackageDataSource
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class PackageDataSourceImpl @Inject constructor(): PackageDataSource {

    val packageList = mutableListOf<PackageInfo>()

    private val packageInfo = MutableLiveData<List<PackageInfo>>()
    private val _packageInfo:LiveData<List<PackageInfo>> = packageInfo

    override suspend fun fetchPackageInfo(context: Context){


        Log.d("testing", "fetchPackageInfo: flow start")

        val packages = context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        for (packageInfo in packages.withIndex()) {

            extractPackages(context,packageInfo.index,packageInfo.value)
//            Log.d("TAG", "Source dir : " + packageInfo.value)
//            Log.d(
//                "TAG",
//                "Launch Activity :" + context.packageManager.getLaunchIntentForPackage(packageInfo.value.packageName)
//            )
        }

        packageInfo.postValue(packageList)





    }

    override fun getPackageInfo(): LiveData<List<PackageInfo>> {
        return _packageInfo
    }


    fun extractPackages(context: Context, index: Int, applicationInfo: ApplicationInfo) {
        val name = context.packageManager.getApplicationLabel(applicationInfo)
        val logo = applicationInfo.loadIcon(context.packageManager)


        val info = context.packageManager.getPackageInfo(applicationInfo.packageName,0)
        val versionName = info.versionName
        val versionCode = info.versionCode

        packageList.add(PackageInfo(index,name.toString(),applicationInfo.packageName,versionName,versionCode,logo))

        Log.d("TAG", "Installed package :"  + logo)
    }


}