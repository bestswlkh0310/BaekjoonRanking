package com.bestswlkh0310.presentation.feature.onboard.start

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.presentation.R
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentStartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment: BaseFragment<FragmentStartBinding, StartViewModel>() {
    override val viewModel: StartViewModel by viewModels()
    override fun observerViewModel() {
    }

    override fun onStart() {
        super.onStart()
        initButtonListener()
    }


    private fun initButtonListener() {
        with(mBinding) {
            btnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_startFragment_to_loginFragment)
            }
            btnSignup.setOnClickListener {
                findNavController().navigate(R.id.action_startFragment_to_signupFragment)
            }
        }
    }
}