package com.bestswlkh0310.presentation.feature.onboard.login

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.presentation.R
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentLoginBinding
import com.bestswlkh0310.presentation.feature.onboard.login.LoginViewModel.Companion.LOGIN
import com.bestswlkh0310.presentation.util.Constant.TAAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModels()
    override fun observerViewModel() {
        with(viewModel) {
            nickName.observe(this@LoginFragment) {
                nickNameState.value = it != ""
            }
        }
        bindingViewEvent {
            when (it) {
//                LOGIN -> findNavController().navigate(R.id)
            }
        }
    }
}