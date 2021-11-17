package dev.prabhatpandey.triviagameandroid.data.models

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("clues_count")
    val cluesCount: Int,
    @SerializedName("created_at")
    val createdAt: String,
    val id: Int,
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
)