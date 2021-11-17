package dev.prabhatpandey.triviagameandroid.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import dev.prabhatpandey.triviagameandroid.ui.models.Question
import dev.prabhatpandey.triviagameandroid.utils.getOrAwaitValueTest

import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created By Prabhat Pandey for TriviaGameAndroid project
 * on Tuesday, 16 November, 2021 at 6:38 PM
 */

class TriviaViewModelTest {

    /**
     * It makes sure that each task executes synchronously.
     */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TriviaViewModel

    @Before
    fun setUp() {
        viewModel = TriviaViewModel()
    }

    @Test
    fun setQuestion_ShouldUpdateQuestionLiveData() {
        viewModel.setQuestion(
            Question(
                title = "Question Title",
                answer = "Question Answer",
                category = "Question Category"
            )
        )

        val question = viewModel.question.getOrAwaitValueTest()
        assertThat(question).isNotNull()
    }

    @Test
    fun setQuestion_ShouldUpdateQuestionLiveDataWithCorrectTitle() {
        viewModel.setQuestion(
            Question(
                title = "Question Title",
                answer = "Question Answer",
                category = "Question Category"
            )
        )

        val question = viewModel.question.getOrAwaitValueTest()
        assertThat(question.title).isEqualTo("Question Title")
    }

    @Test
    fun setQuestion_ShouldUpdateQuestionLiveDataWithCorrectAnswer() {
        viewModel.setQuestion(
            Question(
                title = "Question Title",
                answer = "Question Answer",
                category = "Question Category"
            )
        )

        val question = viewModel.question.getOrAwaitValueTest()
        assertThat(question.answer).isEqualTo("Question Answer")
    }

    @Test
    fun checkAnswer_WhenCorrectAnswer_ShouldUpdateAnswerLiveDataWithTrue() {
        viewModel.setQuestion(
            Question(
                title = "Question Title",
                answer = "Question Answer",
                category = "Question Category"
            )
        )

        viewModel.checkAnswer("Question Answer")
        val isCorrect = viewModel.isAnswerCorrect.getOrAwaitValueTest().peekContent()
        assertThat(isCorrect).isTrue()

    }


    @Test
    fun checkAnswer_WhenCorrectAnswer_ShouldUpdateAnswerLiveDataWithFalse() {
        viewModel.setQuestion(
            Question(
                title = "Question Title",
                answer = "Question Answer",
                category = "Question Category"
            )
        )

        viewModel.checkAnswer("Incorrect Answer")
        val isCorrect = viewModel.isAnswerCorrect.getOrAwaitValueTest().peekContent()
        assertThat(isCorrect).isFalse()

    }

    @Test
    fun setAnswerInput_WhenInputIsNotEmpty_ShouldSetIsSubmitBtnEnabledToTrue() {
        viewModel.setAnswerInput(input = "Answer")

        val isEnabled = viewModel.isSubmitEnabled.getOrAwaitValueTest()

        assertThat(isEnabled).isTrue()
    }


    @Test
    fun setAnswerInput_WhenInputIsEmpty_ShouldSetIsSubmitBtnEnabledToFalse() {
        viewModel.setAnswerInput(input = "")

        val isEnabled = viewModel.isSubmitEnabled.getOrAwaitValueTest()

        assertThat(isEnabled).isFalse()
    }



}