package com.bestswlkh0310.presentation.feature.onboard.start

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.presentation.R
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentStartABinding
import com.bestswlkh0310.presentation.databinding.FragmentStartBBinding
import com.bestswlkh0310.presentation.databinding.FragmentStartCBinding
import com.bestswlkh0310.presentation.databinding.FragmentStartDBinding
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragmentD: BaseFragment<FragmentStartDBinding, StartViewModelD>() {
    override val viewModel: StartViewModelD by viewModels()
    override fun observerViewModel() {
    }

    override fun onStart() {
        super.onStart()
        initButtonListener()
    }

    private fun initButtonListener() {
        with(mBinding) {
            btnStart.setOnClickListener {
                val state = slide.panelState
                if (state == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    slide.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                } else if (state == SlidingUpPanelLayout.PanelState.EXPANDED) {
                    slide.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
                }
            }
            main.setOnClickListener {
                slide.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            }
            btnSignIn.setOnClickListener {
                findNavController().navigate(R.id.action_startFragmentD_to_signInFragment)
            }
            btnSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_startFragmentD_to_signUpFragment)
            }
        }
    }
}