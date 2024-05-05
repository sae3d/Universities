package com.xische.task.contract

import com.xische.common.navigation.BaseNavigation
import com.xische.list.contract.UniversitiesPresentationContract
import com.xische.task.navigation.NavigationEvents
import javax.inject.Inject

class UniversitiesPresentationContractImpl @Inject constructor(private val navigation: BaseNavigation<NavigationEvents>) :
    UniversitiesPresentationContract {
    override fun onUniversityClick(name: String) {
        navigation.onEvent(NavigationEvents.NavigateToDetails(name))
    }

}