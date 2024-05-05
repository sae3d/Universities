package com.xische.list.di

import com.xische.list.data.repository.UniversitiesRepoImpl
import com.xische.list.domain.repository.UniversitiesRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UniversitiesModule {

    @Binds
    abstract fun bindUniversitiesRepo(impl: UniversitiesRepoImpl): UniversitiesRepo

}