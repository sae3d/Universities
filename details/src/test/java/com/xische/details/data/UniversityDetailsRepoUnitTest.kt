package com.xische.details.data

import com.xische.common.data.local.UniversitiesLocalDs
import com.xische.common.data.local.UniversityDto
import com.xische.common.data.local.toEntity
import com.xische.common.domain.entity.UniversityEntity
import com.xische.common.misc.testing.BaseTest
import com.xische.details.domain.UniversityDetailsRepo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals


class UniversityDetailsRepoUnitTest : BaseTest() {

    @RelaxedMockK
    lateinit var universitiesLocalDs: UniversitiesLocalDs
    private lateinit var universityDetailsRepo: UniversityDetailsRepo

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        universityDetailsRepo = UniversityDetailsRepoImpl(universitiesLocalDs)
    }

    @Test
    fun `Test when Get University`() = runTest {
        mockkStatic("com.xische.common.data.local.UniversitiesMapperKt")

        // Given
        val item = mockk<UniversityEntity>()
        val dto = mockk<UniversityDto>()

        // When
        coEvery { universitiesLocalDs.getUniversityByName("") } returns dto
        every { dto.toEntity() } returns item

        val result = universityDetailsRepo.getUniversity("")
        assertEquals(Result.success(item), result)


    }
}