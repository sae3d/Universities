package com.xische.task.navigation

import com.xische.common.navigation.BaseNavigation
import com.xische.task.NavGraphDirections

import javax.inject.Inject

class RootFlowNavigation @Inject constructor() :
    BaseNavigation<NavigationEvents>() {
    override fun onEvent(event: NavigationEvents) {
        when (event) {
            is NavigationEvents.NavigateToDetails -> {
                navController?.navigate(
                    NavGraphDirections.actionNavigateToUniversity(event.name)
                )
            }

            is NavigationEvents.Back -> navController?.popBackStack()
        }
    }
}