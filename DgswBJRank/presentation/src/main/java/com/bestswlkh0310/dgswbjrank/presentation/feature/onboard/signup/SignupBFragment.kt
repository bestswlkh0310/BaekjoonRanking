package com.bestswlkh0310.dgswbjrank.presentation.feature.onboard.signup

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bestswlkh0310.databinding.FragmentSignupBBinding
import com.bestswlkh0310.dgswbjrank.presentation.base.BaseFragment
import com.bestswlkh0310.dgswbjrank.util.Constant.TAAG

class SignupBFragment: BaseFragment<FragmentSignupBBinding, SignupBViewModel>() {
    override val viewModel: SignupBViewModel by viewModels()

    private val args by navArgs<SignupBFragmentArgs>()

    override fun observerViewModel() {

    }

    override fun onStart() {
        super.onStart()
        initButtonListener()
        Log.d(TAAG, "${args.bjId}, ${args.nickName} - onStart() called")
    }

    private fun initButtonListener() {
        with(mBinding) {
            btnNext.setOnClickListener {
                viewModel.signup()
            }
        }
    }
}