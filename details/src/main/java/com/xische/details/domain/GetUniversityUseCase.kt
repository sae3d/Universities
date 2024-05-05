package com.xische.details.domain

import javax.inject.Inject

class GetUniversityUseCase @Inject constructor(private val universityDetailsRepo: UniversityDetailsRepo) {

    suspend operator fun invoke(param: String) = universityDetailsRepo.getUniversity(param)
}