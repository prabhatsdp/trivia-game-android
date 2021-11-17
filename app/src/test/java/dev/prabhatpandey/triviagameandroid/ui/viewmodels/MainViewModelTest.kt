package dev.prabhatpandey.triviagameandroid.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import dev.prabhatpandey.triviagameandroid.data.repositories.FakeTriviaRepositoryImpl
import dev.prabhatpandey.triviagameandroid.ui.viewmodels.MainViewModel
import dev.prabhatpandey.triviagameandroid.utils.FakeDispatcherProvider
import dev.prabhatpandey.triviagameandroid.utils.Status
import dev.prabhatpandey.triviagameandroid.utils.getOrAwaitValueTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created By Prabhat Pandey for TriviaGameAndroid project
 * on Tuesday, 16 November, 2021 at 9:32 AM
 */

class MainViewModelTest {

    /**
     * It makes sure that each task executes synchronously.
     */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    private var repository = FakeTriviaRepositoryImpl()

    /**
     * This runs before tests so that the ViewModel is initialized before the test and
     * we don't need to initialize the ViewModel on each test.
     */
    @Before
    fun setup() {
        viewModel = MainViewModel(FakeDispatcherProvider(), repository)
    }

    @Test
    fun fetchQuestion_WhenServerWorking_ShouldGetSuccessResponse() {
        viewModel.fetchQuestion()

        val questionEvent = viewModel.question.getOrAwaitValueTest()

        assertThat(questionEvent.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun fetchQuestion_WhenServerWorking_ShouldUpdateQuestionLiveDataWithQuestion() {
        viewModel.fetchQuestion()
        val questionEvent = viewModel.question.getOrAwaitValueTest()
        val question = questionEvent.getContentIfNotHandled()?.data?.question

        assertThat(question).isNotNull()
    }

    @Test
    fun fetchQuestion_WhenServerNotWorking_ShouldGetErrorResponse() {
        repository.setShouldReturnNetworkError(true)
        viewModel.fetchQuestion()

        val questionEvent = viewModel.question.getOrAwaitValueTest()
        assertThat(questionEvent.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
        repository.setShouldReturnNetworkError(false)
    }

}