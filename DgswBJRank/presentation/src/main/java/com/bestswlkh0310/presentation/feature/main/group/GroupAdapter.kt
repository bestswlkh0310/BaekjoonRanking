package com.bestswlkh0310.presentation.feature.main.group

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bestswlkh0310.presentation.databinding.ItemGroupBinding

class GroupAdapter(
    private val friendList: List<String>
): RecyclerView.Adapter<GroupAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemGroupBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener {
                val friend = friendList[adapterPosition]
            }
        }
    }

    override fun getItemCount() = friendList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}