package com.xische.list.data

import com.xische.common.data.local.UniversitiesLocalDs
import com.xische.common.data.local.UniversityDto
import com.xische.common.data.local.toDto
import com.xische.common.data.local.toEntity
import com.xische.common.domain.entity.UniversityEntity
import com.xische.common.misc.testing.BaseTest
import com.xische.list.data.repository.UniversitiesRepoImpl
import com.xische.list.data.source.mapper.toEntity
import com.xische.list.data.source.remote.UniversitiesRemoteDs
import com.xische.list.data.source.remote.UniversityModel
import com.xische.list.domain.repository.UniversitiesRepo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.runs
import io.mockk.verify
import org.junit.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UniversitiesRepoUnitTest:BaseTest() {

    @RelaxedMockK
    lateinit var universitiesRemoteDs: UniversitiesRemoteDs

    @RelaxedMockK
    lateinit var universitiesLocalDs: UniversitiesLocalDs
    private lateinit var universitiesRepo: UniversitiesRepo

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        universitiesRepo = UniversitiesRepoImpl(universitiesRemoteDs, universitiesLocalDs)
    }

    @Test
    fun `Test when Get Universities Success`() = runTest {
        // Given
        mockkStatic("com.xische.list.data.source.mapper.UniversitiesMapperKt")
        val item = mockk<UniversityEntity>(relaxed = true)
        val model = mockk<UniversityModel>(relaxed = true)

        // When
        coEvery { universitiesRemoteDs.getUniversities() } returns listOf(model)
        every { model.toEntity() } returns item

        val result = universitiesRepo.getUniversities()

        // Then
        assertEquals(Result.success(listOf(item)), result)
        verify { model.toEntity() }


    }

    @Test
    fun `Test when Get Universities Failure`() = runTest {


        try {
            // When
            coEvery { universitiesRemoteDs.getUniversities() } throws Exception()

            val result = universitiesRepo.getUniversities()

            // Then
            assertEquals(Result.failure<List<UniversityEntity>>(Exception()), result)
        } catch (ex: Exception) {
        }

    }

    @Test
    fun `Test when Get Cached Universities`() = runTest {
        // Given
        mockkStatic("com.xische.common.data.local.UniversitiesMapperKt")
        val item = mockk<UniversityEntity>(relaxed = true)
        val dto = mockk<UniversityDto>(relaxed = true)

        // When
        coEvery { universitiesLocalDs.getUniversities() } returns listOf(dto)
        every { dto.toEntity() } returns item

        val result = universitiesRepo.getCachedUniversities()

        // Then
        assertEquals(Result.success(listOf(item)), result)
        verify { dto.toEntity() }

    }

    @Test
    fun `Test when cache Universities`() = runTest {
        // Given
        mockkStatic("com.xische.common.data.local.UniversitiesMapperKt")
        val item = mockk<UniversityEntity>(relaxed = true)
        val dto = mockk<UniversityDto>(relaxed = true)

        // When
        coEvery { universitiesLocalDs.cacheUniversities(listOf(dto)) } just runs
        every { item.toDto() } returns dto

        val result = universitiesRepo.cacheUniversities(listOf(item))

        // Then
        assertEquals(Result.success(Unit), result)
        verify { item.toDto() }

    }
}