package com.xische.common.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UniversityDto::class], version = 1)
abstract class UniversitiesDataBase : RoomDatabase() {

    abstract fun universitiesDao(): UniversitiesDao

}