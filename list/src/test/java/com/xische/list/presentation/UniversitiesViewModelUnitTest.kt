package com.xische.list.presentation

import com.xische.common.domain.entity.UniversityEntity
import com.xische.common.misc.testing.BaseTest
import com.xische.common.presentation.uimodel.UniversityUiModel
import com.xische.common.presentation.uimodel.toUiModel
import com.xische.list.domain.interactor.GetUniversitiesUseCase
import com.xische.list.presentation.uimodel.UniversitiesDataUiModel
import com.xische.list.presentation.viewmodel.UniversitiesViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals


class UniversitiesViewModelUnitTest :BaseTest(){

    @RelaxedMockK
    lateinit var getUniversitiesUseCase: GetUniversitiesUseCase
   private lateinit var universitiesViewModel: UniversitiesViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        universitiesViewModel = UniversitiesViewModel(getUniversitiesUseCase)
    }

    @Test
    fun `Test when Get Universities Success`() = runTest {
        // Given
        val uiModel = mockk<UniversityUiModel>()
        val entity = mockk<UniversityEntity>()
        mockkStatic("com.xische.common.presentation.uimodel.UniversitiesUIMapperKt")


        // When
        coEvery { getUniversitiesUseCase() } returns Result.success(listOf(entity))
        every { entity.toUiModel() } returns uiModel

        universitiesViewModel.getUniversities()

        // Then
        assertEquals(
            UniversitiesDataUiModel(
                loading = false,
                error = false,
                data = listOf(uiModel)
            ), universitiesViewModel.uiModel.value
        )
        verify { entity.toUiModel() }


    }

    @Test
    fun `Test when Get Universities Failure`() = runTest {


        // When
        coEvery { getUniversitiesUseCase() } returns Result.failure(Exception())

        universitiesViewModel.getUniversities()

        // Then
        assertEquals(
            UniversitiesDataUiModel(
                loading = false,
                error = true,
                data = emptyList()
            ), universitiesViewModel.uiModel.value
        )

    }
}