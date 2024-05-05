package com.xische.list.data

import com.xische.common.misc.testing.BaseServiceApiUnitTest
import com.xische.list.data.source.remote.UniversitiesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class UniversitiesApiUnitTest : BaseServiceApiUnitTest<UniversitiesApi>() {

    override fun provideApiClass(): Class<UniversitiesApi> = UniversitiesApi::class.java

    @Test
    fun `Test when Get Universities Success`() = runTest {

        // When
        enqueueResponse(fileName = UNIVERSITIES_RESPONSE_FILE_NAME)

        val apiResult = api.getUniversities("")

        // Then
        with(apiResult) {
            assertEquals(1,size)
            assertEquals("state",get(0).state)
            assertEquals("country",get(0).country)
            assertEquals("name",get(0).name)
            assertEquals("code",get(0).code)
            assertEquals(1,get(0).webPages.size)

        }
    }

    companion object {
        private const val UNIVERSITIES_RESPONSE_FILE_NAME =
            "response/universities_response.json"
    }

}