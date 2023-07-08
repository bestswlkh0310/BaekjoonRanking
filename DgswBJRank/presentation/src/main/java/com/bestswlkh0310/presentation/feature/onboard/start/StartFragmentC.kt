package com.bestswlkh0310.presentation.feature.onboard.start

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.presentation.R
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentStartABinding
import com.bestswlkh0310.presentation.databinding.FragmentStartBBinding
import com.bestswlkh0310.presentation.databinding.FragmentStartCBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragmentC: BaseFragment<FragmentStartCBinding, StartViewModelC>() {
    override val viewModel: StartViewModelC by viewModels()
    override fun observerViewModel() {
    }

    override fun onStart() {
        super.onStart()
        initButtonListener()
    }


    private fun initButtonListener() {
        mBinding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_startFragmentC_to_startFragmentD)
        }
    }
}