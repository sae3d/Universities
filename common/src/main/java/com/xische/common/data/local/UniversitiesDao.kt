package com.xische.common.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UniversitiesDao {

    @Query("SELECT * FROM Universities")
    suspend fun getUniversities(): List<UniversityDto>?

    @Query("SELECT * FROM Universities WHERE name=:name")
    suspend fun getUniversityByName(name:String):UniversityDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUniversities(universities:List<UniversityDto>)

}