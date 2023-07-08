package com.bestswlkh0310.presentation.feature.onboard.sign_up

import androidx.fragment.app.viewModels
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentSignUpBinding
import com.bestswlkh0310.presentation.feature.onboard.sign_up.SignUpViewModel.Companion.NOT_FOUND_BJ_ID
import com.bestswlkh0310.presentation.feature.onboard.sign_up.SignUpViewModel.Companion.WRONG_ID
import com.bestswlkh0310.presentation.feature.onboard.sign_up.SignUpViewModel.Companion.WRONG_INPUT
import com.bestswlkh0310.presentation.feature.onboard.sign_up.SignUpViewModel.Companion.WRONG_PW
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment: BaseFragment<FragmentSignUpBinding, SignUpViewModel>() {
    override val viewModel: SignUpViewModel by viewModels()
    override fun observerViewModel() {
        with(viewModel) {
        }

        bindingViewEvent { event ->
            when (event) {
                NOT_FOUND_BJ_ID -> showToast("백준 아이디를 다시 입력해 주세요")
                /*FOUND_BJ_ID -> findNavController().navigate(SignupAFragmentDirections.actionSignupAFragmentToSignupBFragment(
                    viewModel.bjId.value!!,
                    viewModel.nickName.value!!,
                    viewModel.pw.value!!
                ))*/
                WRONG_PW -> showToast("8~12자 특수 문자, 영어, 숫자가 포함된 비밀 번호를 입력해 주세요")
                WRONG_INPUT -> showToast("아이디 비밀 번호를 입력해 주세요")
                WRONG_ID -> showToast("4~12자 영어로 된 닉네임를 입력해 주세요")
            }
        }
    }
}