package com.wit.voguely.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.wit.voguely.R
import com.wit.voguely.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpNavigation()
        setUpToolbar()

    }

    private fun setUpNavigation() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
                    as NavHostFragment
        val navController = navHostFragment.findNavController()
        val appBarConfiguration = AppBarConfiguration(binding.bottomNavigation.menu)
        binding.bottomNavigation.setupWithNavController(navController)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun setUpToolbar() {
        binding.toolbar.inflateMenu(R.menu.main_menu)
        binding.toolbar.menu.findItem(R.id.signOut).setOnMenuItemClickListener {
            viewModel.signOut()
            findNavController().navigate(R.id.action_mainFragment_to_loginSignUpFragment)
            return@setOnMenuItemClickListener false
        }
    }
}