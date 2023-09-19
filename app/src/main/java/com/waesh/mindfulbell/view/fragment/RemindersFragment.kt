package com.waesh.mindfulbell.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.waesh.mindfulbell.R
import com.waesh.mindfulbell.application.MindfulApplication
import com.waesh.mindfulbell.databinding.FragmentRemindersBinding
import com.waesh.mindfulbell.view.ItemClickActions
import com.waesh.mindfulbell.view.RemindersAdapter
import com.waesh.mindfulbell.viewmodel.RemindersViewModel
import com.waesh.mindfulbell.viewmodel.RemindersViewModelFactory

class RemindersFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentRemindersBinding
    private val viewModel by viewModels<RemindersViewModel> {
        RemindersViewModelFactory((requireActivity().application as MindfulApplication).repository)
    }

    private var mainMenu: Menu? = null


    private lateinit var recyclerView: RecyclerView

    private val adapter = RemindersAdapter(object : ItemClickActions {

        override fun onClick(holderPosition: Int, viewId: Int) {
            //TODO
        }

    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRemindersBinding.inflate(layoutInflater, container, false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = adapter
        viewModel.reminders.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        mainMenu = menu
        menuInflater.inflate(R.menu.reminders_menu, menu)
        hideMenu()
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.action_cancel -> {}
            R.id.action_edit -> {}
            R.id.action_delete -> {}
        }
        return true
    }

    private fun hideMenu() {
        mainMenu?.findItem(R.id.action_delete)?.isVisible = false
        mainMenu?.findItem(R.id.action_edit)?.isVisible = false
        mainMenu?.findItem(R.id.action_cancel)?.isVisible = false
    }

    private fun showMenu() {
        mainMenu?.findItem(R.id.action_delete)?.isVisible = true
        mainMenu?.findItem(R.id.action_edit)?.isVisible = true
        mainMenu?.findItem(R.id.action_cancel)?.isVisible = true
    }

}