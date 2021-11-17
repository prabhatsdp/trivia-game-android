package dev.prabhatpandey.triviagameandroid.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Created By Prabhat Pandey for TriviaGameAndroid project
 * on Tuesday, 16 November, 2021 at 7:46 PM
 */

class ExtensionsKtTest {

    @Test
    fun capitalizeWords_ShouldCapitalizeTextCorrectly() {
        val string = "this is a String".capitalizeWords()
        assertThat(string).isEqualTo("This Is A String")
    }

    @Test
    fun capitalizeWords_WhenCapitalize_TextValueShouldBeTheSame() {
        val string = "this is a string".capitalizeWords()
        assertThat(string.lowercase()).isEqualTo("this is a string")
    }

    @Test
    fun capitalizeWords_WhenCapitalize_ShouldNotChangeUppercaseWords() {
        val string = "this is a STRING".capitalizeWords()
        assertThat(string).isEqualTo("This Is A STRING")
    }

    @Test
    fun capitalizeWords_WhenFirstCharacterOtherThanAlphabet_ShouldThrowError() {
        val string = "this is a \"big\" string".capitalizeWords()

        assertThat(string).isEqualTo("This Is A \"big\" String")
    }
}