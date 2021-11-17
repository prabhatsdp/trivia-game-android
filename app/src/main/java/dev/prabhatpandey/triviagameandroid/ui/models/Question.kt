package dev.prabhatpandey.triviagameandroid.ui.models

import android.os.Parcelable
import dev.prabhatpandey.triviagameandroid.utils.capitalizeWords
import kotlinx.parcelize.Parcelize

/**
 * Created By Prabhat Pandey for TriviaGameAndroid project
 * on Tuesday, 16 November, 2021 at 7:55 AM
 */
@Parcelize
data class Question(
    val title: String,
    val answer: String,
    val category: String
) : Parcelable {
    override fun toString(): String {
        return "Question { " +
                "title: $title, " +
                "answer: $answer, " +
                "category: $category " +
                "}"
    }

    /**
     * Returns category after capitalizing each word of it.
     */
    fun getCategoryCapitalized() : String {

        return category.capitalizeWords()
    }

}