package com.xische.common.presentation.uimodel

import com.xische.common.domain.entity.UniversityEntity

fun UniversityEntity.toUiModel() = UniversityUiModel(
    name = name,
    state = state,
    webPage = webPage,
    code = code,
    country = country
)