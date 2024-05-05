package com.xische.task.di

import com.xische.details.contract.UniversityPresentationContract
import com.xische.list.contract.UniversitiesPresentationContract
import com.xische.task.contract.UniversitiesPresentationContractImpl
import com.xische.task.contract.UniversityPresentationContractImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class ContractModule {
    @Binds
    abstract fun bindUniversitiesPresentationContract(impl: UniversitiesPresentationContractImpl): UniversitiesPresentationContract

    @Binds
    abstract fun bindUniversityPresentationContract(impl: UniversityPresentationContractImpl): UniversityPresentationContract

}