package com.wit.voguely.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.wit.voguely.R
import com.wit.voguely.databinding.FragmentHomeBinding
import com.wit.voguely.model.Products
import com.wit.voguely.ui.main.pdp.AddedSuccessfully
import com.wit.voguely.ui.main.pdp.ItemAdded
import com.wit.voguely.ui.main.pdp.PDPFragment.Companion.PRODUCT_ID_ARG
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeFragmentViewModel

    private val adapter: ProductsAdapter = ProductsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productData.collectLatest {
                adapter.updateItems(it)
//                adapter.notifyDataSetChanged() replaced to diffUtil

            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.liveProgressBar.collectLatest {
                binding.progressbar.isVisible = it
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.event.collectLatest { event ->
                setMessage(event)
            }
        }
        adapter.onItemClick = {
            productClicked(it)
        }
        adapter.dropDownClick = { product, view ->
            onDropDownMenuClick(product, view)
        }
        binding.recycleView.adapter = adapter
    }


    private fun productClicked(product: Products) {
        val bundle = Bundle()
        bundle.putString(PRODUCT_ID_ARG, product.id)

        parentFragment
            ?.parentFragment
            ?.findNavController()
            ?.navigate(R.id.action_mainFragment_to_PDPFragment, bundle)
    }

    private fun onDropDownMenuClick(product: Products, view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.pop_up_menu, popupMenu.menu)
        popupMenu.show()

        popupMenu.setOnMenuItemClickListener {
            viewModel.addProduct(product.id)
            return@setOnMenuItemClickListener false
        }
    }

    private fun setMessage(event: ItemAdded) {
        when (event) {
            is AddedSuccessfully -> Toast.makeText(
                requireContext(),
                "Item added to cart", // TODO: Extract string resource
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}