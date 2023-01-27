package com.wit.voguely.ui.main.pdp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.wit.voguely.databinding.FragmentPDPBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PDPFragment : Fragment() {
    private lateinit var binding: FragmentPDPBinding
    private lateinit var viewModel: PDPViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[PDPViewModel::class.java]
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
        val idProduct = arguments?.getString("id")

        if (idProduct != null) {
            viewModel.oneProductLoad(idProduct)
        }

        lifecycleScope.launch {
            viewModel.product.collectLatest {
                if (it != null) {
                    Glide
                        .with(requireContext())
                        .load(it.image)
                        .into(binding.bigimage)

                    binding.fullname.text = it.name
                    binding.rating.text = it.rating.toString()
                    binding.description.text = it.description
                    val amount = it.price.toString()
                    val eur = it.currency
                    binding.priceTag.text = eur + amount
                }
            }
        }

        binding.addToCart.setOnClickListener {
            if (idProduct != null) {
                viewModel.addProduct(idProduct)
            }
        }
        lifecycleScope.launch {
            viewModel.event.collectLatest { event ->
                setMessage(event)
            }
        }
    }

    private fun setMessage(event: ItemAdded) {
        when (event) {
            is AddedSuccessfully -> Toast.makeText(
                requireContext(),
                "Item added to cart",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}