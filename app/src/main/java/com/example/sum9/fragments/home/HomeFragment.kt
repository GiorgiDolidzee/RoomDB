package com.example.sum9.fragments.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sum9.ItemsAdapter
import com.example.sum9.databinding.FragmentHomeBinding
import com.example.sum9.fragments.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var itemsAdapter: ItemsAdapter

    override fun start() {
        initRecyclerView()
        observes()
    }

    private fun initRecyclerView() {
        itemsAdapter = ItemsAdapter()
        binding.homeRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = itemsAdapter
        }

    }

    private fun observes() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                val item = viewModel.setData()
//                d("viewModel", "$item")
//            }
//        }
        viewModel.data.observe(viewLifecycleOwner, {result ->
            when(result) {

            }
        })
    }

}