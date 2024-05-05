package com.xische.list.data.source.remote


import javax.inject.Inject

class UniversitiesRemoteDs @Inject constructor(private val universitiesApi: UniversitiesApi) {


    suspend fun getUniversities(): List<UniversityModel> {

        return universitiesApi.getUniversities("United Arab Emirates")


    }
}