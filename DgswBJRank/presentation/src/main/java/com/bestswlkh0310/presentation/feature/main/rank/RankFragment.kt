package com.bestswlkh0310.presentation.feature.main.rank

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentRankBinding
import com.bestswlkh0310.presentation.feature.main.rank.RankViewModel.Companion.ADD_ALL_RANK
import com.bestswlkh0310.presentation.feature.main.rank.RankViewModel.Companion.REMOVE_ALL
import com.bestswlkh0310.presentation.feature.main.rank.adapter.RankAdapter
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.cache.DiskLruCache.Companion.REMOVE

@AndroidEntryPoint
class RankFragment: BaseFragment<FragmentRankBinding, RankViewModel>() {
    override val viewModel: RankViewModel by viewModels()

    lateinit var adapter: RankAdapter

    override fun observerViewModel() {
        bindingViewEvent { event ->
            when (event) {
                ADD_ALL_RANK -> adapter.notifyItemInserted(viewModel.rankList.value!!.size)
                REMOVE_ALL -> adapter.notifyItemRangeRemoved(0, viewModel.rankList.value!!.size)
            }
        }
        with(viewModel) {
            seasonTotal.observe(this@RankFragment) { mBinding.total.text = "대소고 전체 " + it.toString() + "문제 "}
        }
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
        with(viewModel) {
            reload()
            getAllToday()
            getSeasonTotal()
        }
    }

    private fun initRecyclerView() {
        with(mBinding) {
            adapter = RankAdapter(viewModel.rankList.value!!) { bjId, nickName ->
                showToast("bjId - $bjId nickName - $nickName")
            }
            rvRank.adapter = adapter
            rvRank.layoutManager = LinearLayoutManager(activity)
        }
    }
}