package dev.prabhatpandey.triviagameandroid.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prabhatpandey.triviagameandroid.data.models.QuestionResponse
import dev.prabhatpandey.triviagameandroid.data.repositories.TriviaRepository
import dev.prabhatpandey.triviagameandroid.utils.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By Prabhat Pandey for TriviaGameAndroid project
 * on Tuesday, 16 November, 2021 at 7:17 AM
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val repository: TriviaRepository
) : ViewModel() {

    private val _question = MutableLiveData<Event<Resource<QuestionResponse>>>()
    val question: LiveData<Event<Resource<QuestionResponse>>> = _question

    fun fetchQuestion() {

        _question.value = Event(Resource.loading(null))

        viewModelScope.launch(dispatchers.io) {
            val response = repository.getQuestion()
            _question.postValue(Event(response))
        }

    }


    companion object {
        private const val TAG = "TriviaViewModel"
    }
}