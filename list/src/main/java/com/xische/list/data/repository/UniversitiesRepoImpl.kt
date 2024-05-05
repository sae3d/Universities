package com.xische.list.data.repository

import com.xische.common.misc.Completable
import com.xische.common.data.local.UniversitiesLocalDs
import com.xische.common.data.local.toDto
import com.xische.common.data.local.toEntity
import com.xische.common.domain.entity.UniversityEntity
import com.xische.list.data.source.mapper.toEntity
import com.xische.list.data.source.remote.UniversitiesRemoteDs
import com.xische.list.domain.repository.UniversitiesRepo
import okio.IOException
import javax.inject.Inject

class UniversitiesRepoImpl @Inject constructor(
    private val universitiesRemoteDs: UniversitiesRemoteDs,
    private val universitiesLocalDs: UniversitiesLocalDs
) : UniversitiesRepo {
    override suspend fun getUniversities(): Result<List<UniversityEntity>> {
        return try {
            val result = universitiesRemoteDs.getUniversities().map { it.toEntity() }
            return Result.success(result)
        } catch (exception: IOException) {
            Result.failure(exception)
        }

    }

    override suspend fun getCachedUniversities(): Result<List<UniversityEntity>> {
        val result = universitiesLocalDs.getUniversities()
        return if (result.isNullOrEmpty()) Result.failure(Exception())
        else Result.success(result.map { it.toEntity() })
    }

    override suspend fun cacheUniversities(universities: List<UniversityEntity>): Completable {
        universitiesLocalDs.cacheUniversities(universities.map { it.toDto() })
        return Result.success(Unit)
    }
}