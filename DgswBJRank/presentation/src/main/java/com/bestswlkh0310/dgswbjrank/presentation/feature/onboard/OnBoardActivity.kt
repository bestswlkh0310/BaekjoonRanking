package com.bestswlkh0310.dgswbjrank.presentation.feature.onboard

import androidx.activity.viewModels
import com.bestswlkh0310.databinding.ActivityOnBoardBinding
import com.bestswlkh0310.dgswbjrank.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardActivity: BaseActivity<ActivityOnBoardBinding, OnBoardViewModel>() {
    override val viewModel: OnBoardViewModel by viewModels()

    override fun observerViewModel() {

    }
}