package com.xische.common.misc.testing

import org.junit.Rule


open class BaseTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
}