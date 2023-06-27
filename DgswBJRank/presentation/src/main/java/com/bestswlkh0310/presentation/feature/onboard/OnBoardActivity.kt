package com.bestswlkh0310.presentation.feature.onboard

import androidx.activity.viewModels
import com.bestswlkh0310.presentation.base.BaseActivity
import com.bestswlkh0310.presentation.databinding.ActivityOnBoardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardActivity: BaseActivity<ActivityOnBoardBinding, OnBoardViewModel>() {
    override val viewModel: OnBoardViewModel by viewModels()

    override fun observerViewModel() {

    }
}