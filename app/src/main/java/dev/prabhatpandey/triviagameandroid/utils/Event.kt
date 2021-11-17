package dev.prabhatpandey.triviagameandroid.utils

/**
 * Created By Prabhat Pandey for TriviaGameAndroid project
 * on Tuesday, 16 November, 2021 at 10:36 AM
 */


/**
 * Helps in live data observation from views like fragments and activity
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}