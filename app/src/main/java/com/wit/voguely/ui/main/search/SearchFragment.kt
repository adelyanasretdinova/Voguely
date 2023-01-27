package com.wit.voguely.ui.main.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wit.voguely.R
import com.wit.voguely.databinding.FragmentSearchBinding
import com.wit.voguely.ui.main.Products
import com.wit.voguely.ui.main.RecyclerViewAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val adapter: RecyclerViewAdapter = RecyclerViewAdapter()
    private lateinit var viewModel: SearchViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.searchedItems.collectLatest {
                adapter.itemsList = it
                adapter.notifyDataSetChanged()
            }
        }

        lifecycleScope.launch{
            viewModel.noResult.collectLatest {
                binding.group.isVisible = it
            }
        }

        adapter.onItemClick = {
            productClicked(it)
        }

        adapter.dropDownClick = { product, view ->
            onDropDownMenuClick (product, view)
        }

        binding.searchbar.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {

                viewModel.search(s)
            }
        })

        binding.productview.adapter = adapter
    }

    private fun productClicked(product: Products) {
        val bundle = Bundle()
        bundle.putString("id", product.id)

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

}