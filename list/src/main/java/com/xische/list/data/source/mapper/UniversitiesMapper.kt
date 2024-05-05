package com.xische.list.data.source.mapper

import com.xische.common.domain.entity.UniversityEntity
import com.xische.list.data.source.remote.UniversityModel

fun UniversityModel.toEntity() = UniversityEntity(
    name = name,
    country = country,
    state = state ?: "",
    code = code,
    webPage = webPages[0]
)