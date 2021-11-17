package dev.prabhatpandey.triviagameandroid.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.prabhatpandey.triviagameandroid.NavGraphDirections
import dev.prabhatpandey.triviagameandroid.R
import dev.prabhatpandey.triviagameandroid.databinding.ActivityMainBinding
import dev.prabhatpandey.triviagameandroid.ui.models.Question
import dev.prabhatpandey.triviagameandroid.ui.viewmodels.MainViewModel
import dev.prabhatpandey.triviagameandroid.utils.Status

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mMainViewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navGraphContainer) as NavHostFragment

        navController =  navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.startScreen,
                R.id.triviaScreen
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        setupViews()
        setupListeners()
        setupObservers()
    }


    private fun setupViews() {
        // TODO: 11/16/2021 Setup Views

    }

    private fun setupListeners() {
        // TODO: 11/16/2021 Setup Listeners
    }

    private fun setupObservers() {
        mMainViewModel.question.observe(this) {
            it.getContentIfNotHandled()?.let { questionResource ->
                when (questionResource.status) {
                    Status.ERROR -> {
                        // error state
                        binding.progressBar.visibility = View.GONE
                        Log.d(TAG, "setupObservers: Error: ${questionResource.message}")
                    }
                    Status.SUCCESS -> {
                        // success state launch question fragment
                        binding.progressBar.visibility = View.GONE
                        Log.d(TAG, "setupObservers: Success => ${questionResource.data}")
                        val question = questionResource.data?.let { ques ->
                            Question(
                                title = ques.question,
                                answer = ques.answer,
                                category = ques.category.title
                            )
                        }

                        if (question != null) {
                            findNavController(R.id.navGraphContainer).navigate(
                                NavGraphDirections.actionGlobalTriviaScreen(
                                    question
                                )
                            )
                        } else {
                            Toast.makeText(this, R.string.invalid_response, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                    Status.LOADING -> {
                        // show loading bar
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }

}