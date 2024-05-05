package com.xische.common.navigation

import androidx.navigation.NavController

abstract class BaseNavigation<T> {
    var navController: NavController? = null
    fun init(navController: NavController?) {
        this.navController = navController
    }

     fun start() {}
    abstract fun onEvent(event: T)


}