package com.bestswlkh0310.dgswbjrank.presentation.feature.main

import androidx.activity.viewModels
import com.bestswlkh0310.databinding.ActivityMainBinding
import com.bestswlkh0310.dgswbjrank.presentation.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()

    override fun observerViewModel() {
        
    }
}