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

        setupViews()
        setupObservers()
    }


    private fun setupViews() {
        // get the nav host fragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navGraphContainer) as NavHostFragment

        // initialize the nav controller
        navController = navHostFragment.navController

        //initialize the app bar configuration
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.startScreen,
                R.id.triviaScreen
            )
        )

        // setup action bar so that it changes the title according to the destination
        // and shows the back button if applicable
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    /**
     * Attaches the observer to the live data objects of MainViewModel
     */
    private fun setupObservers() {

        // sets up the observer on question live data so that when question loads
        // the page changes to new question screen
        mMainViewModel.question.observe(this) {
            it.getContentIfNotHandled()?.let { questionResource ->
                when (questionResource.status) {
                    Status.ERROR -> {
                        // error state
                        binding.progressBar.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        // success state launch new question fragment
                        binding.progressBar.visibility = View.GONE

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