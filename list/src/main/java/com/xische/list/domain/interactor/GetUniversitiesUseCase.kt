package com.xische.list.domain.interactor

import com.xische.common.misc.andThen
import com.xische.common.domain.entity.UniversityEntity
import com.xische.common.misc.flatMapDelayError
import com.xische.list.domain.repository.UniversitiesRepo
import javax.inject.Inject

class GetUniversitiesUseCase @Inject constructor(private val universitiesRepo: UniversitiesRepo) {

    suspend operator fun invoke(): Result<List<UniversityEntity>> {

        return universitiesRepo.getUniversities().flatMapDelayError(
            transform = {
                universitiesRepo.cacheUniversities(it).andThen {
                    universitiesRepo.getCachedUniversities()
                }.getOrThrow()
            },
            elseTransform = { universitiesRepo.getCachedUniversities() }
        )

    }
}



