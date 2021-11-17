package dev.prabhatpandey.triviagameandroid.data.repositories

import android.util.Log
import dev.prabhatpandey.triviagameandroid.data.TriviaApi
import dev.prabhatpandey.triviagameandroid.data.models.QuestionResponse
import dev.prabhatpandey.triviagameandroid.utils.Resource
import java.lang.Exception
import javax.inject.Inject

/**
 * Created By Prabhat Pandey for TriviaGameAndroid project
 * on Tuesday, 16 November, 2021 at 7:50 AM
 */

class TriviaRepositoryImpl @Inject constructor(
    private val triviaApi: TriviaApi
) : TriviaRepository {

    /**
     * Fetches a new random question from API using trivia API
     */
    override suspend fun getQuestion(): Resource<QuestionResponse> {
        return try {
            val res = triviaApi.getQuestion()
            val result = res.body()

            Log.d(TAG, "getQuestion: Result Body: $result")

            if (res.isSuccessful && result != null && result.isNotEmpty()) {
                Log.d(TAG, "getQuestion: Response is successful")
                val questionResponse = result[0]
                Resource.success(data = questionResponse)
            } else {
                Log.d(TAG, "getQuestion: Response is unsuccessful")
                Resource.error(msg = res.message(), data = null)
            }
        } catch (e: Exception) {
            Log.d(TAG, "getQuestion: Exception => $e")
            Resource.error(
                msg = "Couldn't connect to the server, check your internet connection.",
                data = null
            )
        }
    }

    companion object {
        private const val TAG = "TriviaRepositoryImpl"
    }

}