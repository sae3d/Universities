package com.xische.list.domain

import com.xische.common.domain.entity.UniversityEntity
import com.xische.common.misc.testing.BaseTest
import com.xische.list.domain.interactor.GetUniversitiesUseCase
import com.xische.list.domain.repository.UniversitiesRepo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals


class GetUniversitiesUseCaseUnitTest:BaseTest() {

    @RelaxedMockK
    lateinit var universitiesRepo: UniversitiesRepo
    private lateinit var getUniversitiesUseCase: GetUniversitiesUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getUniversitiesUseCase = GetUniversitiesUseCase(universitiesRepo)
    }

    @Test
    fun `Test when Get Universities Success`() = runTest {

        // Given
        val item = mockk<UniversityEntity>()

        // When
        coEvery { universitiesRepo.getUniversities() } returns Result.success(listOf(item))
        coEvery { universitiesRepo.cacheUniversities(listOf(item)) } returns Result.success(Unit)
        coEvery { universitiesRepo.getCachedUniversities() } returns Result.success(listOf(item))

        val result = getUniversitiesUseCase()

        // Then
        assertEquals(Result.success(listOf(item)), result)
        coVerify { universitiesRepo.cacheUniversities(listOf(item)) }
        coVerify { universitiesRepo.getCachedUniversities() }
        coVerify { universitiesRepo.getUniversities() }


    }
    @Test
    fun `Test when Get Universities Failure And Cache is Empty`() = runTest {

        // Given
        val item = mockk<UniversityEntity>()

        // When
        coEvery { universitiesRepo.getUniversities() } returns Result.failure(Exception())
        coEvery { universitiesRepo.getCachedUniversities() } returns Result.failure(Exception())

        val result = getUniversitiesUseCase()

        // Then
        assertEquals(false, result.isSuccess)
        coVerify(inverse = true) { universitiesRepo.cacheUniversities(listOf(item))}
        coVerify { universitiesRepo.getCachedUniversities() }
        coVerify { universitiesRepo.getUniversities() }


    }

    @Test
    fun `Test when Get Universities Failure And Cache is Not Empty`() = runTest {

        // Given
        val item = mockk<UniversityEntity>()

        // When
        coEvery { universitiesRepo.getUniversities() } returns Result.failure(Exception())
        coEvery { universitiesRepo.getCachedUniversities() } returns Result.success(listOf(item))

        val result = getUniversitiesUseCase()

        // Then
        assertEquals(Result.success(listOf(item)), result)
        coVerify(inverse = true) { universitiesRepo.cacheUniversities(listOf(item))}
        coVerify { universitiesRepo.getCachedUniversities() }
        coVerify { universitiesRepo.getUniversities() }


    }
}