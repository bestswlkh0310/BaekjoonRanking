package com.bestswlkh0310.dgswbjrank.presentation.feature.onboard.start

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.R
import com.bestswlkh0310.databinding.FragmentStartBinding
import com.bestswlkh0310.dgswbjrank.presentation.base.BaseFragment

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