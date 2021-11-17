package dev.prabhatpandey.triviagameandroid.data.repositories

import dev.prabhatpandey.triviagameandroid.data.models.Category
import dev.prabhatpandey.triviagameandroid.data.models.QuestionResponse
import dev.prabhatpandey.triviagameandroid.utils.Resource

/**
 * Created By Prabhat Pandey for TriviaGameAndroid project
 * on Tuesday, 16 November, 2021 at 10:50 AM
 */

class FakeTriviaRepositoryImpl : TriviaRepository {

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getQuestion(): Resource<QuestionResponse> {
        if (shouldReturnNetworkError) {
            return Resource.error(msg = "Network Error", data = null)
        } else {
            return Resource.success(
                data = QuestionResponse(
                    answer = "Answer",
                    question = "Question",
                    airDate = "airDate",
                    category = Category(
                        cluesCount = 10,
                        id = 1,
                        title = "Category Title",
                        createdAt = "Created At",
                        updatedAt = "Updated At",
                    ),
                    categoryId = 1,
                    createdAt = "Date",
                    gameId = 1,
                    id = 1,
                    invalidCount = 10,
                    updatedAt = "Updated At",
                    value = 100
                )
            )
        }
    }
}