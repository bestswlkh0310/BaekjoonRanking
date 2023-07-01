package com.bestswlkh0310.presentation.feature.main.rank.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bestswlkh0310.presentation.databinding.ItemRankBinding
import com.bestswlkh0310.domain.dto.RankModel

class RankAdapter(
    private val friendList: List<RankModel>
): RecyclerView.Adapter<RankAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemRankBinding): RecyclerView.ViewHolder(binding.root) {
        val rank = binding.rank
        val nickName = binding.nickName
        val solve = binding.solve
        val balloonView = binding.balloonView
        /*init {
            binding.cardView.setOnClickListener {
                val friend = friendList[adapterPosition]
                Log.d(TAAG, "${friend.nickName}, ${friend.value}, ${friend.date} - onCreateViewHolder() called")
                val cx = balloonView.width / 2
                val cy = balloonView.height / 2
                val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

                val animator: Animator = ViewAnimationUtils.createCircularReveal(balloonView, cx, cy, 0f, finalRadius)
                animator.duration = 1000
                balloonView.visibility = View.VISIBLE
                animator.start()
                animator.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        balloonView.visibility = View.INVISIBLE
                    }
                })
            }
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRankBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener {

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