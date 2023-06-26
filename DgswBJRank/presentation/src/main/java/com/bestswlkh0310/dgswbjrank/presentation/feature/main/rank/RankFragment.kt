package com.bestswlkh0310.dgswbjrank.presentation.feature.main.rank

import androidx.fragment.app.viewModels
import com.bestswlkh0310.databinding.FragmentLoginBinding
import com.bestswlkh0310.databinding.FragmentRankBinding
import com.bestswlkh0310.dgswbjrank.presentation.base.BaseFragment

class RankFragment: BaseFragment<FragmentRankBinding, RankViewModel>() {
    override val viewModel: RankViewModel by viewModels()
    override fun observerViewModel() {

    }
}