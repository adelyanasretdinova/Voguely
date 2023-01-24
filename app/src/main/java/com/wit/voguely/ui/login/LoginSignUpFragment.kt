package com.wit.voguely.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.wit.voguely.R
import com.wit.voguely.databinding.FragmentLoginSignUpBinding
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
        lifecycleScope.launch{
            viewModel.event.collectLatest { event ->
                setEvent(event)
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

        binding.loginSignUpButton.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            viewModel.buttonClicked(email, password)
        }
    }

    private fun navigateToMain() {
        findNavController().navigate(R.id.action_loginSignUpFragment_to_mainFragment)
    }

    private fun setEvent(event: LoginEvent) {
        when (event) {
            is LoginFail -> Toast.makeText(
                requireContext(),
                "Incorrect email or passport",
                Toast.LENGTH_LONG).show()
            is LoginSuccess -> navigateToMain()
        }
    }

    private fun setSelectedTabText(selectedTab: SelectedTab) {
        binding.welcomeText.setText(selectedTab.welcomeMessage)
        binding.loginSignUpButton.setText(selectedTab.buttonText)
    }
}