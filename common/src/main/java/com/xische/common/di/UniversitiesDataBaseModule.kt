package com.xische.common.di

import android.content.Context
import androidx.room.Room
import com.xische.common.data.local.UniversitiesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class UniversitiesDataBaseModule {

    @ActivityRetainedScoped
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): UniversitiesDataBase {
        return Room.databaseBuilder(context, UniversitiesDataBase::class.java, "Universities.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}