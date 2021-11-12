package com.example.sum9

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sum9.databinding.ItemCardBinding
import com.example.sum9.model.ItemModel

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {

    private var items = mutableListOf<ItemModel>()

    inner class ItemsViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: ItemModel
        fun onBind() {
            model = items[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemsViewHolder (
        ItemCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount() = items.size
}