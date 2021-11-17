package dev.prabhatpandey.triviagameandroid.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dev.prabhatpandey.triviagameandroid.R
import dev.prabhatpandey.triviagameandroid.databinding.FragmentStartBinding
import dev.prabhatpandey.triviagameandroid.ui.viewmodels.MainViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [StartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private var _binding : FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStartBinding.inflate(inflater, container, false)
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
        // TODO: 11/16/2021 Setup Views
    }

    private fun setupListeners() {
        // TODO: 11/16/2021 Setup Listeners
        binding.run {
            startGameBtn.setOnClickListener {
                mainViewModel.fetchQuestion()
            }
        }
    }


    private fun setupObservers() {

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment StartFragment.
         */
        @JvmStatic
        fun newInstance() =
            StartFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}