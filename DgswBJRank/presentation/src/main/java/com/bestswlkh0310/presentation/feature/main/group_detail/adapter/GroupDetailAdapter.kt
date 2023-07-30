package com.bestswlkh0310.presentation.feature.main.group_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bestswlkh0310.presentation.databinding.ItemRankBinding
import com.bestswlkh0310.domain.entity.UserModel

class GroupDetailAdapter(
    private val userList: List<UserModel>,
    private val onclick: (String, String) -> Unit
): RecyclerView.Adapter<GroupDetailAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemRankBinding): RecyclerView.ViewHolder(binding.root) {
        val rank = binding.rank
        val nickName = binding.nickName
        val solve = binding.solve
        val balloonView = binding.balloonView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRankBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener {

            }
        }
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.rank.text = (position + 1).toString()
        holder.nickName.text = user.nickName
//        holder.solve.text = user.value.toString() + " 문제"
//        holder.itemView.setOnClickListener {
//            onclick(user.bjId, user.nickName!!)
//        }
    }
}