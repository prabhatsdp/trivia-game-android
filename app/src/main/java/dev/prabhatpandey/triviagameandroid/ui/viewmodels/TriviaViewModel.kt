package dev.prabhatpandey.triviagameandroid.ui.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prabhatpandey.triviagameandroid.ui.models.Question
import dev.prabhatpandey.triviagameandroid.utils.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * Created By Prabhat Pandey for TriviaGameAndroid project
 * on Tuesday, 16 November, 2021 at 6:20 PM
 */
@HiltViewModel
class TriviaViewModel @Inject constructor() : ViewModel() {

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question> = _question

    private val _isAnswerCorrect = MutableLiveData<Event<Boolean>>()
    val isAnswerCorrect: LiveData<Event<Boolean>> = _isAnswerCorrect

    private val _answerInput = MutableLiveData<String>()


    val isSubmitEnabled : LiveData<Boolean> = _answerInput.map {
        return@map it.isNotEmpty()
    }


    /**
     * Sets the answer input live data with given [input]
     */
    fun setAnswerInput(input: String) {
        _answerInput.value = input
    }

    /**
     * Sets the question live data with given [ques]
     */
    fun setQuestion(ques: Question) {
        _question.value = ques
    }

    /**
     * Checks if the answer is correct or not and saves the boolean value
     * in the isAnswerCorrect live data.
     */
    fun checkAnswer(answer: String) {
        val ques = question.value
        if (ques != null) {
            _isAnswerCorrect.value = Event(ques.answer == answer)
        } else {
            _isAnswerCorrect.value = Event(false)
        }
    }

}