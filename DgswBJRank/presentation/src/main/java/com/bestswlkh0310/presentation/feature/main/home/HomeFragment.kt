package com.bestswlkh0310.presentation.feature.main.home

import androidx.fragment.app.viewModels
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentHomeBinding

class HomeFragment: BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override fun observerViewModel() {

    }
}