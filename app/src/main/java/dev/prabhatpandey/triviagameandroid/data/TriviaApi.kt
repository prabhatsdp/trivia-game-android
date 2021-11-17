package dev.prabhatpandey.triviagameandroid.data

import dev.prabhatpandey.triviagameandroid.data.models.QuestionResponse
import dev.prabhatpandey.triviagameandroid.utils.RANDOM
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created By Prabhat Pandey for TriviaGameAndroid project
 * on Tuesday, 16 November, 2021 at 7:35 AM
 */

interface TriviaApi {
    @GET(RANDOM)
    suspend fun getQuestion(): Response<List<QuestionResponse>>
}