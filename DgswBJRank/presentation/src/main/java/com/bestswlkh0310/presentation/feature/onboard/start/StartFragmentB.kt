package com.bestswlkh0310.presentation.feature.onboard.start

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.presentation.R
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentStartABinding
import com.bestswlkh0310.presentation.databinding.FragmentStartBBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragmentB: BaseFragment<FragmentStartBBinding, StartViewModelB>() {
    override val viewModel: StartViewModelB by viewModels()
    override fun observerViewModel() {
    }

    override fun onStart() {
        super.onStart()
        initButtonListener()
    }


    private fun initButtonListener() {
        mBinding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_startFragmentB_to_startFragmentC)
        }
    }
}