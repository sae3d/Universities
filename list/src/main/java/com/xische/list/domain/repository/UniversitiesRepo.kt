package com.xische.list.domain.repository

import com.xische.common.misc.Completable
import com.xische.common.domain.entity.UniversityEntity

interface UniversitiesRepo {

    suspend fun getUniversities(): Result<List<UniversityEntity>>
    suspend fun getCachedUniversities(): Result<List<UniversityEntity>>
    suspend fun cacheUniversities(universities: List<UniversityEntity>) : Completable
}