package com.xische.common.data.local

import javax.inject.Inject

class UniversitiesLocalDs @Inject constructor(private val universitiesDataBase: UniversitiesDataBase) {


    suspend fun getUniversities() = universitiesDataBase.universitiesDao().getUniversities()

    suspend fun getUniversityByName(name: String) =
        universitiesDataBase.universitiesDao().getUniversityByName(name)

    suspend fun cacheUniversities(universities: List<UniversityDto>) =
        universitiesDataBase.universitiesDao().insertUniversities(universities)
}