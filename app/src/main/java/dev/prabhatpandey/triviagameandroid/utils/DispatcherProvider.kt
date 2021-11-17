package dev.prabhatpandey.triviagameandroid.utils

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created By Prabhat Pandey for TriviaGameAndroid project
 * on Tuesday, 16 November, 2021 at 7:32 AM
 */

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}