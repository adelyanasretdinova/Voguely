package com.wit.voguely.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wit.voguely.R
import com.wit.voguely.databinding.FragmentPDPBinding
import com.wit.voguely.databinding.FragmentSearchBinding


class PDPFragment : Fragment() {
    private lateinit var binding: FragmentPDPBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPDPBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.bigimage.setImageResource(arguments?.getInt("photo")) }
//        binding.fullname.text = arguments?.getString("name")

    }
}