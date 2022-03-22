package com.akash.appscheduler

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.akash.appscheduler.Repositories.DataSource.PackageDataSource
import com.akash.appscheduler.Repositories.DataSourceImpl.PackageDataSourceImpl
import com.akash.appscheduler.Repositories.PackageRepository
import com.akash.appscheduler.Viewmodels.MainViewModel
import com.akash.appscheduler.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    lateinit var binding:ActivityMainBinding

    val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val navController = findNavController(R.id.nav_host_fragment)
        // Hook your navigation controller to bottom navigation view
        binding.bottomNavView.setupWithNavController(navController)






    }

    override fun onResume() {
        super.onResume()
        mainViewModel.fetchPackageData()
    }




    companion object{
        private const val TAG = "MainActivity"
    }
}