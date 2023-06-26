package com.bestswlkh0310.dgswbjrank.presentation.feature.onboard.login

import androidx.fragment.app.viewModels
import com.bestswlkh0310.databinding.FragmentLoginBinding
import com.bestswlkh0310.dgswbjrank.presentation.base.BaseFragment

class LoginFragment: BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModels()
    override fun observerViewModel() {

    }
}