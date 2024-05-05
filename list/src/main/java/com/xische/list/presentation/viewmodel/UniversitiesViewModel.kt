package com.xische.list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xische.list.domain.interactor.GetUniversitiesUseCase
import com.xische.common.presentation.uimodel.toUiModel
import com.xische.list.presentation.uimodel.UniversitiesDataUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversitiesViewModel @Inject constructor(private val getUniversitiesUseCase: GetUniversitiesUseCase) :
    ViewModel() {


    private val _uiModel = MutableStateFlow(UniversitiesDataUiModel())
    val uiModel = _uiModel.asStateFlow()


    init {
        getUniversities()

    }

     fun getUniversities() {

        viewModelScope.launch {
            _uiModel.value =
                UniversitiesDataUiModel(loading = true)

            getUniversitiesUseCase().fold(
                onSuccess = {
                    _uiModel.value =
                        UniversitiesDataUiModel(data = it.map { university -> university.toUiModel() })
                },
                onFailure = {
                    _uiModel.value =
                        UniversitiesDataUiModel(error = true)
                }
            )
        }
    }

}