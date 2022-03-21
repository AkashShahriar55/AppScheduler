package com.akash.appscheduler.Repositories.DataSourceImpl

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.akash.appscheduler.Models.PackageInfo
import com.akash.appscheduler.Repositories.DataSource.PackageDataSource
import com.akash.appscheduler.Worker.PackageFetcher
import kotlinx.coroutines.flow.Flow

class PackageDataSourceImpl: PackageDataSource {
    override suspend fun fetchPackageInfo(context: Context):List<PackageInfo> {
        val packageList = mutableListOf<PackageInfo>()
        val packages = context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        for (packageInfo in packages.withIndex()) {
            packageList.add(PackageInfo(packageInfo.index,packageInfo.value.name,packageInfo.value.packageName,"",0,packageInfo.value.loadIcon(context.packageManager)))
            Log.d("TAG", "Installed package :" + packageInfo.value.packageName)
            Log.d("TAG", "Source dir : " + packageInfo.value.sourceDir)
            Log.d(
                "TAG",
                "Launch Activity :" + context.packageManager.getLaunchIntentForPackage(packageInfo.value.packageName)
            )
        }

        return packageList
    }
}