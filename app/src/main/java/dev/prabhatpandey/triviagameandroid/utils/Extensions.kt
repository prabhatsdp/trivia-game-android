package dev.prabhatpandey.triviagameandroid.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

/**
 * Created By Prabhat Pandey for TriviaGameAndroid project
 * on Tuesday, 16 November, 2021 at 7:39 PM
 */

fun String.capitalizeWords() : String {
    return this.split(" ").joinToString(" ") { str ->
        str.replaceFirstChar {
            it.uppercase()
        }
    }
}

