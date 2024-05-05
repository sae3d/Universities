package com.xische.details.di

import com.xische.details.data.UniversityDetailsRepoImpl
import com.xische.details.domain.UniversityDetailsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class UniversityModule {

    @Binds
    abstract fun bindUniversitiesRepo(impl: UniversityDetailsRepoImpl): UniversityDetailsRepo

}
