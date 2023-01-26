package com.wit.voguely.ui.main.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.wit.voguely.databinding.FragmentCartBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel
    private val adapter: CartRecyclerViewAdapter = CartRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.productinCart.collectLatest {
                adapter.productsInCart = it
                adapter.notifyDataSetChanged()
            }
        }

        lifecycleScope.launch {
            viewModel.emptyCart.collectLatest {
                binding.groupEmpty.isVisible = it
            }
        }
        lifecycleScope.launch {
            viewModel.totalAmount.collectLatest {
                        binding.totalAmout.text = "EUR ${it}"
            }
        }


        binding.cartRecyclerView.adapter = adapter
    }

}