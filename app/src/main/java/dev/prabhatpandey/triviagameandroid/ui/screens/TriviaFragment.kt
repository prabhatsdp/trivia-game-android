package dev.prabhatpandey.triviagameandroid.ui.screens

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.prabhatpandey.triviagameandroid.R
import dev.prabhatpandey.triviagameandroid.databinding.FragmentTriviaBinding
import dev.prabhatpandey.triviagameandroid.ui.viewmodels.MainViewModel
import dev.prabhatpandey.triviagameandroid.ui.viewmodels.TriviaViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.google.android.material.snackbar.Snackbar.SnackbarLayout


/**
 * A simple [Fragment] subclass.
 * Use the [TriviaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class TriviaFragment : Fragment() {

    private val args: TriviaFragmentArgs by navArgs()

    private val mainViewModel: MainViewModel by activityViewModels()
    private val triviaViewModel: TriviaViewModel by viewModels()
    private lateinit var answerTextChangeListener: TextWatcher

    private var _binding: FragmentTriviaBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        triviaViewModel.setQuestion(args.question)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTriviaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // other ui code
        setupViews()
        setupListeners()
        setupObservers()
    }

    private fun setupViews() {

        triviaViewModel.setAnswerInput("")
        answerTextChangeListener = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // no use
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                triviaViewModel.setAnswerInput(text.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
                // no use
            }
        }
    }

    private fun setupListeners() {

        binding.submitBtn.setOnClickListener {
            triviaViewModel.checkAnswer(binding.answerEt.text.toString().trim())
        }

    }


    private fun setupObservers() {

        triviaViewModel.question.observe(viewLifecycleOwner) { question ->
            binding.questionCategory.text = question.getCategoryCapitalized()
            val title = "${question.title}?"
            binding.question.text = title
        }

        triviaViewModel.isSubmitEnabled.observe(viewLifecycleOwner) {
            binding.submitBtn.isEnabled = it
        }

        triviaViewModel.isAnswerCorrect.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { isCorrect ->

                // show the correct or incorrect answer snackbar
                showSnackBar(isCorrect)
                // Disable EditText and button so that user cannot enter
                // data again and press submit while the new question is loading.
                disableEditTextAndButton()
                // fetch new question after a delay of 2 seconds so that user can see
                // if the answer is correct or incorrect
                fetchNewQuestion()
            }
        }
    }

    /**
     * Disables the Answer EditText and Submit Button
     */
    private fun disableEditTextAndButton() {
        binding.answerLayout.isEnabled = false
        binding.submitBtn.isEnabled = false
    }


    /**
     * Shows the Correct or Incorrect Snackbar base on [isCorrect] value.
     */
    private fun showSnackBar(isCorrect: Boolean) {

        // create an instance of the snackbar
        val snackbar = Snackbar.make(binding.root, "", Snackbar.LENGTH_LONG)

        // inflate the custom_snackbar_view created previously
        val customSnackView: View = if (isCorrect) {
            layoutInflater.inflate(R.layout.snackbar_correct, null)
        } else {
            layoutInflater.inflate(R.layout.snackbar_incorrect, null)
        }

        // set the background of the default snackbar as transparent
        snackbar.view.setBackgroundColor(Color.TRANSPARENT)

        // now change the layout of the snackbar
        val snackbarLayout = snackbar.view as SnackbarLayout

        // set padding of the all corners as 0

        // set padding of the all corners as 0
        snackbarLayout.setPadding(0, 0, 0, 0)
        snackbarLayout.addView(customSnackView, 0)
        snackbar.show()
    }

    /**
     * Calls MainViewModel's fetchQuestion method after a delay of 2 seconds.
     */
    private fun fetchNewQuestion() {
        lifecycleScope.launch {
            delay(2000)
            mainViewModel.fetchQuestion()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.answerEt.addTextChangedListener(answerTextChangeListener)
    }

    override fun onPause() {
        super.onPause()
        // remove text change listener
        binding.answerEt.removeTextChangedListener(answerTextChangeListener)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        @JvmStatic
        fun newInstance() =
            TriviaFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}