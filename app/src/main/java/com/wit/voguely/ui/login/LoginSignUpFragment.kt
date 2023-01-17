package com.wit.voguely.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.wit.voguely.databinding.FragmentLoginSignUpBinding
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.wit.voguely.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginSignUpFragment : Fragment() {
    private lateinit var binding: FragmentLoginSignUpBinding
    private lateinit var viewModel: LoginSignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginSignUpViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.selectedTab.collectLatest { selectedTab ->
                setSelectedTabText(selectedTab)
            }
        }


        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(selectedTab: TabLayout.Tab) {
                val loginTab = binding.tabLayout.getTabAt(0)?.isSelected ?: false
                viewModel.onSelectedTabChanged(if (loginTab) SelectedTab.LOGIN else SelectedTab.SIGN_UP)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

        binding.loginSignUpButton.setOnClickListener{
            findNavController().navigate(R.id.action_loginSignUpFragment_to_mainFragment)
        }
    }
    private fun setSelectedTabText(selectedTab: SelectedTab) {
//        if (selectedTab == SelectedTab.LOGIN) {
//            binding.welcomeText.text = getString(R.string.welcome_back)
//            binding.loginSignUpButton.text =
//                getString(R.string.login)
//        } else {
//            binding.welcomeText.text = getString(R.string.join_now)
//            binding.loginSignUpButton.text =
//                getString(R.string.signup)
//        }
        binding.welcomeText.setText(selectedTab.welcomeMessage)
        binding.loginSignUpButton.setText(selectedTab.buttonText)
    }
}