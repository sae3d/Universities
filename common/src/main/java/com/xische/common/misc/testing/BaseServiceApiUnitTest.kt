package com.xische.common.misc.testing

import io.mockk.MockKAnnotations
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import java.net.HttpURLConnection
import java.nio.charset.StandardCharsets

/**
 * Abstract class that provides the base configuration to test api classes.
 * How to use it:
 * 1. Override [provideApiClass] method that provides class of service api that you test;
 * 2. Call [enqueueResponse] in your GIVEN block;
 * 3. You can use [assertRequestPath] to check the called path.
 */

@RunWith(JUnit4::class)
abstract class BaseServiceApiUnitTest<T : Any> : BaseTest() {

    abstract fun provideApiClass(): Class<T>

    private lateinit var mockWebServer: MockWebServer

    protected lateinit var api: T

    @Before
    fun setupMockServer() {
        MockKAnnotations.init(this)
        mockWebServer = MockWebServer()
        mockWebServer.start()
        api = createService()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    fun enqueueResponse(fileName: String) {
        val inputStream = javaClass.classLoader?.getResourceAsStream("$fileName")
        val source = inputStream?.source()?.buffer()
        source?.let {
            val mockResponse = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(it.readString(StandardCharsets.UTF_8))
            mockWebServer.enqueue(mockResponse)
        }
    }

    fun assertRequestPath(path: String) {
        val request: RecordedRequest = mockWebServer.takeRequest()
        MatcherAssert.assertThat(request.path, CoreMatchers.`is`(path))
    }

    private fun createService(): T {
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(provideApiClass())
    }
}