package com.akash.appscheduler.Viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.akash.appscheduler.Models.PackageInfo
import com.akash.appscheduler.Repositories.PackageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(
    val  packageRepository: PackageRepository, application: Application
): AndroidViewModel(application) {


    init {

    }


    fun fetchPackageData(){
        viewModelScope.launch(Dispatchers.Default) {
            packageRepository.fetchPackageData(getApplication())
        }

    }


    fun getPackageInfo():LiveData<List<PackageInfo>>{
        return packageRepository.getPackageInfo()
    }






}