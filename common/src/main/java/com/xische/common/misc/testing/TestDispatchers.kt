package com.xische.common.misc.testing

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher


@OptIn(ExperimentalCoroutinesApi::class)
object TestDispatchers {
    private val unconfined = UnconfinedTestDispatcher()
    val test = StandardTestDispatcher()

    val io: CoroutineDispatcher = unconfined
    val main: MainCoroutineDispatcher = Dispatchers.Main
}