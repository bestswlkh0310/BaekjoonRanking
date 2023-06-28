package com.bestswlkh0310.presentation.feature.onboard.login

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bestswlkh0310.presentation.R
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentLoginBinding
import com.bestswlkh0310.presentation.feature.onboard.login.LoginViewModel.Companion.CAN_NOT_LOGIN
import com.bestswlkh0310.presentation.feature.onboard.login.LoginViewModel.Companion.LOGIN
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
                LOGIN -> Toast.makeText(activity, "로그인 성공@!", Toast.LENGTH_SHORT).show()
                CAN_NOT_LOGIN -> Toast.makeText(activity, "로그인 실패 OTL", Toast.LENGTH_SHORT).show()
            }
        }
    }
}