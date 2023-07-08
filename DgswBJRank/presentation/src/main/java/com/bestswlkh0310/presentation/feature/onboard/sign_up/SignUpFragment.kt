package com.bestswlkh0310.presentation.feature.onboard.sign_up

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.presentation.R
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentSignUpBinding
import com.bestswlkh0310.presentation.feature.onboard.sign_up.SignUpViewModel.Companion.EXIST_USER_ID
import com.bestswlkh0310.presentation.feature.onboard.sign_up.SignUpViewModel.Companion.ON_CLICK_BACK
import com.bestswlkh0310.presentation.feature.onboard.sign_up.SignUpViewModel.Companion.ON_CLICK_SIGN_IN
import com.bestswlkh0310.presentation.feature.onboard.sign_up.SignUpViewModel.Companion.SIGN_UP
import com.bestswlkh0310.presentation.feature.onboard.sign_up.SignUpViewModel.Companion.WRONG_ID
import com.bestswlkh0310.presentation.feature.onboard.sign_up.SignUpViewModel.Companion.WRONG_NICKNAME
import com.bestswlkh0310.presentation.feature.onboard.sign_up.SignUpViewModel.Companion.WRONG_INPUT
import com.bestswlkh0310.presentation.feature.onboard.sign_up.SignUpViewModel.Companion.WRONG_PW
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment: BaseFragment<FragmentSignUpBinding, SignUpViewModel>() {
    override val viewModel: SignUpViewModel by viewModels()
    override fun observerViewModel() {
        bindingViewEvent { event ->
            when (event) {
                WRONG_INPUT -> showToast("아이디 비밀 번호를 입력해 주세요")
                WRONG_ID -> showToast("6~12자 특수 문자, 영어, 숫자로 된 아이디를 입력해 주세요")
                WRONG_PW -> showToast("6~12자 특수 문자, 영어, 숫자로 된 비밀 번호를 입력해 주세요")
                WRONG_NICKNAME -> showToast("12자 이하 영어 또는 숫자로 된 닉네임을 입력해 주세요")
                ON_CLICK_SIGN_IN -> findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                SIGN_UP -> {
                    showToast("회원가입 완료")
                    findNavController().popBackStack()
                }
                EXIST_USER_ID -> showToast("이미 존재하는 아이디입니다")
                ON_CLICK_BACK -> findNavController().popBackStack()
            }
        }
    }
}