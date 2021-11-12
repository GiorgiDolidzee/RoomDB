package com.example.sum9.fragments.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sum9.ItemsAdapter
import com.example.sum9.databinding.FragmentHomeBinding
import com.example.sum9.extensions.showSnackbar
import com.example.sum9.fragments.base.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var itemsAdapter: ItemsAdapter

    override fun start() {
        observes()
        initRecyclerView()
    }


    private fun initRecyclerView() {
        itemsAdapter = ItemsAdapter()
        binding.homeRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = itemsAdapter
        }
    }



    private fun observes() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                if(viewModel.isOnline(requireContext())) {
                    view?.showSnackbar("Connected \uD83D\uDC48")
                    viewModel.myData.collect {
                        if(!it.items.isNullOrEmpty()) {
                            itemsAdapter.setData(it.items.toMutableList())
                        }
                    }
                }
                else {
                    view?.showSnackbar("No Internet Connection \uD83D\uDC40")
                    viewModel.items.collect {
                        if(!it.isNullOrEmpty()) {
                            itemsAdapter.setData(it.toMutableList())
                        } else {
                            view?.showSnackbar("Not Found Items")
                        }
                    }
                }

            }
        }
    }

}