package com.wit.voguely

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import com.wit.voguely.databinding.FragmentLoginSignUpBinding
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class LoginSignUpFragment : Fragment() {
    private lateinit var binding: FragmentLoginSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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


        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(selectedTab: TabLayout.Tab) {
                val loginTab = binding.tabLayout.getTabAt(0)
                if (loginTab!= null) {
                    if (selectedTab == loginTab) {
                        binding.welcomeText.text = getString(R.string.welcome_back)
                        binding.loginSignUpButton.text =
                            getString(R.string.login)
                    } else {
                        binding.welcomeText.text = getString(R.string.join_now)
                        binding.loginSignUpButton.text =
                            getString(R.string.signup)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }
}