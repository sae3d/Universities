package com.xische.details.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xische.common.presentation.uimodel.UniversityUiModel
import com.xische.common.presentation.uimodel.toUiModel
import com.xische.details.domain.GetUniversityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversityViewModel @Inject constructor(
    private val getUniversityUseCase: GetUniversityUseCase,
    private val savedStateHandle: SavedStateHandle

) :
    ViewModel() {

    private val _uiModel = MutableStateFlow<UniversityUiModel?>(null)
    val uiModel = _uiModel.asStateFlow()

    init {
        getUniversity()
    }

    private fun getUniversity() {

        viewModelScope.launch {
            getUniversityUseCase(savedStateHandle["name"] ?: "").onSuccess {
                _uiModel.value = it.toUiModel()
            }
        }
    }

}