package dev.prabhatpandey.triviagameandroid.data.models

import com.google.gson.annotations.SerializedName

data class QuestionResponse(
    @SerializedName("airdate")
    val airDate: String,
    val answer: String,
    val category: Category,
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("game_id")
    val gameId: Any,
    val id: Int,
    @SerializedName("invalid_count")
    val invalidCount: Any,
    val question: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    val value: Int
)