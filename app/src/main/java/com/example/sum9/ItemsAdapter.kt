package com.example.sum9

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sum9.databinding.ItemCardBinding
import com.example.sum9.extensions.setImage
import com.example.sum9.model.ItemModel

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {

    private var items = mutableListOf<ItemModel>()

    inner class ItemsViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: ItemModel
        fun onBind() {
            model = items[adapterPosition]
            binding.tvTitle.text = model.title
            binding.tvPrice.text = model.price
            binding.ivItemCover.setImage(model.cover)
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


    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: MutableList<ItemModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}

