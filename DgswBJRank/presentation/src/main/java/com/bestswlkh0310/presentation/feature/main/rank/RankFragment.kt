package com.bestswlkh0310.presentation.feature.main.rank

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentRankBinding
import com.bestswlkh0310.presentation.feature.main.rank.RankViewModel.Companion.ADD_ALL_RANK
import com.bestswlkh0310.presentation.feature.main.rank.adapter.RankAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankFragment: BaseFragment<FragmentRankBinding, RankViewModel>() {
    override val viewModel: RankViewModel by viewModels()

    lateinit var adapter: RankAdapter

    override fun observerViewModel() {
        bindingViewEvent { event ->
            when (event) {
                ADD_ALL_RANK -> adapter.notifyItemInserted(viewModel.rankList.value!!.size)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
        with(viewModel) {
            reload()
            getAllToday()
        }
    }

    private fun initRecyclerView() {
        with(mBinding) {
            adapter = RankAdapter(viewModel.rankList.value!!)
            rvRank.adapter = adapter
            rvRank.layoutManager = LinearLayoutManager(activity)
        }
    }
}