package com.xische.details.domain

import com.xische.common.data.local.UniversityDto
import com.xische.common.domain.entity.UniversityEntity

interface UniversityDetailsRepo {

    suspend fun getUniversity(name: String): Result<UniversityEntity>
}