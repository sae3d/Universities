package com.xische.list.data

import com.xische.common.misc.testing.BaseTest
import com.xische.list.data.source.remote.UniversitiesApi
import com.xische.list.data.source.remote.UniversitiesRemoteDs
import com.xische.list.data.source.remote.UniversityModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals


class UniversitiesRemoteDsUnitTest : BaseTest() {


    @RelaxedMockK
    lateinit var universitiesApi: UniversitiesApi
    private lateinit var universitiesRemoteDs: UniversitiesRemoteDs

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        universitiesRemoteDs = UniversitiesRemoteDs(universitiesApi)
    }

    @Test
    fun `Test when Get Universities Success`() = runTest {

        // Given
        val item = mockk<UniversityModel>()
        // When
        coEvery { universitiesApi.getUniversities("United Arab Emirates") } returns listOf(item)

        val result = universitiesRemoteDs.getUniversities()

        // Then
        with(result)
        {
            assertEquals(1, result.size)
            assertEquals(item, result[0])
        }
    }

}