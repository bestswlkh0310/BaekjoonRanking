package com.bestswlkh0310.presentation.feature.main.find_group

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bestswlkh0310.domain.entity.GroupModel
import com.bestswlkh0310.presentation.databinding.ItemFindGroupBinding

class FindGroupAdapter(
    private val groupList: List<GroupModel>,
    private val callback: (Int) -> Unit
): RecyclerView.Adapter<FindGroupAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemFindGroupBinding): RecyclerView.ViewHolder(binding.root) {
        val name = binding.name
        val description = binding.description
        val memberStatus = binding.memberStatus
        val root = binding.main
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemFindGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
        }
    }

    override fun getItemCount() = groupList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val group = groupList[position]
        with(holder) {
            name.text = group.groupName
            description.text = group.description
            memberStatus.text = "그룹원 ${group.memberCount}/${group.memberLimit}"
            root.setOnClickListener {
                callback(position)
            }
        }
    }
}