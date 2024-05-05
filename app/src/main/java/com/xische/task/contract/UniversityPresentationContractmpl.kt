package com.xische.task.contract

import com.xische.common.navigation.BaseNavigation
import com.xische.details.contract.UniversityPresentationContract
import com.xische.task.navigation.NavigationEvents
import javax.inject.Inject

class UniversityPresentationContractImpl @Inject constructor(
    private val navigation: BaseNavigation<NavigationEvents>
) :
    UniversityPresentationContract {
    override fun back() {
        navigation.onEvent(NavigationEvents.Back)
    }
}