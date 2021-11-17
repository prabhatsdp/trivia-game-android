package dev.prabhatpandey.triviagameandroid.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created By Prabhat Pandey for TriviaGameAndroid project
 * on Tuesday, 16 November, 2021 at 11:23 AM
 */

class FakeDispatcherProvider : DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    override val io: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    override val default: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}