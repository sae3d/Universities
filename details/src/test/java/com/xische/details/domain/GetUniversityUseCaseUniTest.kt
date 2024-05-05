package com.xische.details.domain

import com.xische.common.domain.entity.UniversityEntity
import com.xische.common.misc.testing.BaseTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals


class GetUniversityUseCaseUniTest : BaseTest() {

    private lateinit var getUniversityUseCase: GetUniversityUseCase

    @RelaxedMockK
    lateinit var universityDetailsRepo: UniversityDetailsRepo

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getUniversityUseCase = GetUniversityUseCase(universityDetailsRepo)
    }

    @Test
    fun `Test when Get University`() = runTest {
        //Given
        val item = mockk<UniversityEntity>()

        // When
        coEvery { universityDetailsRepo.getUniversity("") } returns Result.success(item)

        val result = getUniversityUseCase("")

        // Then
        assertEquals(Result.success(item), result)
    }
}