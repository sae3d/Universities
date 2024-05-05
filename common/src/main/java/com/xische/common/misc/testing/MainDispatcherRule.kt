package com.xische.common.misc.testing

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    val testDispatchers: TestDispatchers = TestDispatchers,
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(TestDispatchers.io)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}