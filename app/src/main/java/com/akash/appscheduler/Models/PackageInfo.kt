package com.akash.appscheduler.Models

import android.graphics.drawable.Drawable

data class PackageInfo(
    val id:Int,
    val appName:String,
    val packageName:String,
    val versionName:String?,
    val versionCode:Int = 0,
    val icon:Drawable?
)
