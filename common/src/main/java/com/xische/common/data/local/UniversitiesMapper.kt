package com.xische.common.data.local

import com.xische.common.domain.entity.UniversityEntity

fun UniversityDto.toEntity() = UniversityEntity(
    name = name,
    country = country,
    webPage = webPage,
    state = state,
    code = code
)

fun UniversityEntity.toDto() = UniversityDto(
    name = name,
    country = country,
    webPage = webPage,
    state = state,
    code = code
)

