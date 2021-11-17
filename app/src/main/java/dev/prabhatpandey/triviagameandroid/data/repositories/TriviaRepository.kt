package dev.prabhatpandey.triviagameandroid.data.repositories

import dev.prabhatpandey.triviagameandroid.data.models.QuestionResponse
import dev.prabhatpandey.triviagameandroid.utils.Resource

/**
 * Created By Prabhat Pandey for TriviaGameAndroid project
 * on Tuesday, 16 November, 2021 at 7:30 AM
 */

interface TriviaRepository {

    suspend fun getQuestion(): Resource<QuestionResponse>
}