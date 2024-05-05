package com.xische.details.presentation

import androidx.lifecycle.SavedStateHandle
import com.xische.common.data.local.toDto
import com.xische.common.domain.entity.UniversityEntity
import com.xische.common.misc.testing.BaseTest
import com.xische.common.presentation.uimodel.UniversityUiModel
import com.xische.common.presentation.uimodel.toUiModel
import com.xische.details.domain.GetUniversityUseCase
import com.xische.details.presentation.viewmodel.UniversityViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.spyk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UniversityDetailsViewModelUnitTest : BaseTest() {

    @RelaxedMockK
    lateinit var getUniversityUseCase: GetUniversityUseCase
    private lateinit var universityViewModel: UniversityViewModel
    private val savedStateHandle = SavedStateHandle()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Test when Get University`() = runTest {
        //Given
        val item = mockk<UniversityEntity>()
        val uiModel = mockk<UniversityUiModel>()
        mockkStatic("com.xische.common.presentation.uimodel.UniversitiesUIMapperKt")


        // When
        coEvery { getUniversityUseCase("") } returns Result.success(item)
        every { spyk( savedStateHandle)["name"] ?: "" } returns ""
        coEvery { item.toUiModel() } returns uiModel
        universityViewModel = UniversityViewModel(getUniversityUseCase, savedStateHandle)


        // Then
        assertEquals(uiModel, universityViewModel.uiModel.value)
    }
}