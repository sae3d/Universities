package com.xische.details.data

import com.xische.common.data.local.UniversitiesLocalDs
import com.xische.common.data.local.UniversityDto
import com.xische.common.data.local.toEntity
import com.xische.common.domain.entity.UniversityEntity
import com.xische.details.domain.UniversityDetailsRepo
import javax.inject.Inject

class UniversityDetailsRepoImpl @Inject constructor(private val universitiesLocalDs: UniversitiesLocalDs) :
    UniversityDetailsRepo {
    override suspend fun getUniversity(name: String): Result<UniversityEntity> {
        val result = universitiesLocalDs.getUniversityByName(name)
        return Result.success(result.toEntity())
    }

}