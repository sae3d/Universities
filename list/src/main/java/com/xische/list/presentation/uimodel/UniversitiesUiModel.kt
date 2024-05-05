package com.xische.list.presentation.uimodel

import com.xische.common.presentation.uimodel.UniversityUiModel

data class UniversitiesDataUiModel(
    val loading: Boolean = false,
     val error: Boolean = false,
    val data: List<UniversityUiModel> = emptyList()
)