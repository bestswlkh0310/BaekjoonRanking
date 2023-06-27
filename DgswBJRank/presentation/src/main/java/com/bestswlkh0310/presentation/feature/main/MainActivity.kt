package com.bestswlkh0310.presentation.feature.main

import androidx.activity.viewModels
import com.bestswlkh0310.presentation.base.BaseActivity
import com.bestswlkh0310.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()

    override fun observerViewModel() {
        
    }
}