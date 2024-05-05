package com.xische.task.navigation

sealed class NavigationEvents {
    data class NavigateToDetails(val name: String) : NavigationEvents()
    object Back:NavigationEvents()
}
