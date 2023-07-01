package com.bestswlkh0310.presentation.feature.main.rank.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bestswlkh0310.presentation.databinding.ItemRankBinding
import com.bestswlkh0310.presentation.util.Constant.TAAG
import com.traveling.domain.entity.Rank

class RankAdapter(
    private val friendList: List<Rank>
): RecyclerView.Adapter<RankAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemRankBinding): RecyclerView.ViewHolder(binding.root) {
        val rank = binding.rank
        val nickName = binding.nickName
        val solve = binding.solve
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRankBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener {
                val friend = friendList[adapterPosition]
                Log.d(TAAG, "${friend.nickName}, ${friend.value}, ${friend.date} - onCreateViewHolder() called")
            }
        }
    }

    override fun getItemCount() = friendList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = friendList[position]
        holder.rank.text = (position + 1).toString()
        holder.nickName.text = friend.nickName
        holder.solve.text = friend.value.toString()
    }
}