package com.waesh.mindfulbell.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.waesh.mindfulbell.R
import com.waesh.mindfulbell.application.MindfulApplication
import com.waesh.mindfulbell.databinding.FragmentAppSettingsBinding
import com.waesh.mindfulbell.view.activity.MainActivity
import com.waesh.mindfulbell.viewmodel.ApplicationViewModel
import com.waesh.mindfulbell.viewmodel.ApplicationViewModelFactory

class SettingsFragment : Fragment() {

    private var _binding: FragmentAppSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ApplicationViewModel> {
        ApplicationViewModelFactory((requireActivity().application as MindfulApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dummy()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cvReminders.setOnClickListener{
            (activity as MainActivity).hideBottomNavigationView()
            it.findNavController().navigate(R.id.action_navigation_app_settings_to_remindersFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomNavigationView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun dummy(){
        binding.apply{
            tvIntervalPreview.text = "1 Hour 00 Minute(s)"
            tvSelectedSound.text = "Bell 1"
            tvAudioChannel.text = "Notification"
        }
    }
}