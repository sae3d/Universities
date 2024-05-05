package com.xische.task.di

import com.xische.common.navigation.BaseNavigation
import com.xische.task.navigation.NavigationEvents
import com.xische.task.navigation.RootFlowNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class NavigationModule {
    @Provides
    @ActivityScoped
    fun providesRootFlowNavigation(): BaseNavigation<NavigationEvents> {
        return RootFlowNavigation()
    }
}