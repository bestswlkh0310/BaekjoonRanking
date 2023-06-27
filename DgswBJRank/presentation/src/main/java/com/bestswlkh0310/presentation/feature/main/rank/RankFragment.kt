package com.bestswlkh0310.presentation.feature.main.rank

import androidx.fragment.app.viewModels
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentRankBinding

class RankFragment: BaseFragment<FragmentRankBinding, RankViewModel>() {
    override val viewModel: RankViewModel by viewModels()
    override fun observerViewModel() {

    }
}