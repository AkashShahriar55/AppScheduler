package com.akash.appscheduler.Viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.akash.appscheduler.Models.PackageInfo
import com.akash.appscheduler.Repositories.PackageRepository
import java.util.concurrent.Flow

class MainViewModel(
    val packageRepository: PackageRepository, application: Application
): AndroidViewModel(application) {


    fun fetchPackageData():LiveData<List<PackageInfo>>{
        return packageRepository.fetchPackageData(getApplication())
    }






}