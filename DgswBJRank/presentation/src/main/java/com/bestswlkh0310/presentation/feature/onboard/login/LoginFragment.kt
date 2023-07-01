package com.bestswlkh0310.presentation.feature.onboard.login

import androidx.fragment.app.viewModels
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentLoginBinding
import com.bestswlkh0310.presentation.feature.onboard.OnBoardActivity
import com.bestswlkh0310.presentation.feature.onboard.login.LoginViewModel.Companion.CAN_NOT_LOGIN
import com.bestswlkh0310.presentation.feature.onboard.login.LoginViewModel.Companion.LOGIN
import com.bestswlkh0310.presentation.feature.onboard.login.LoginViewModel.Companion.WRONG_INPUT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModels()
    override fun observerViewModel() {
        with(viewModel) {
            nickName.observe(this@LoginFragment) {
                nickNameState.value = (it != "" || pwState.value!!)
            }
            pw.observe(this@LoginFragment) {
                pwState.value = it != ""
            }
        }
        bindingViewEvent {
            when (it) {
                LOGIN -> if (activity is OnBoardActivity) (activity as OnBoardActivity).startMainActivity()
                CAN_NOT_LOGIN -> showToast("올바른 아이디 비밀 번호를 입력해 주세요")
                WRONG_INPUT -> showToast("아이디 비밀 번호를 입력해 주세요")
            }
        }
    }
}