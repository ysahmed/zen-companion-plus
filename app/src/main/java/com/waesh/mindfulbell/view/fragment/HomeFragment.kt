package com.waesh.mindfulbell.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.waesh.mindfulbell.application.MindfulApplication
import com.waesh.mindfulbell.databinding.FragmentHomeBinding
import com.waesh.mindfulbell.viewmodel.ApplicationViewModel
import com.waesh.mindfulbell.viewmodel.ApplicationViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ApplicationViewModel> {
        ApplicationViewModelFactory((requireActivity().application as MindfulApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.homeText.observe(viewLifecycleOwner){
            it?.let {
                binding.textHome.text = it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}