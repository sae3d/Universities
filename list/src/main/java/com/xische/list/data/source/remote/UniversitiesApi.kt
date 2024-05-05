package com.xische.list.data.source.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface UniversitiesApi {

    @GET("search")
    suspend fun getUniversities(
        @Query("country") country: String,
    ): List<UniversityModel>

}

