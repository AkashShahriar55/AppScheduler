package com.akash.appscheduler

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val navController = findNavController(R.id.nav_host_fragment)
        // Hook your navigation controller to bottom navigation view
        binding.bottomNavView.setupWithNavController(navController)





    }


    companion object{
        private const val TAG = "MainActivity"
    }
}