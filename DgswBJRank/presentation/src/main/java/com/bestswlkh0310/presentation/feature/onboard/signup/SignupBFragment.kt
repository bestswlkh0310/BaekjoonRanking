package com.bestswlkh0310.presentation.feature.onboard.signup

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bestswlkh0310.presentation.R
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentSignupBBinding
import com.bestswlkh0310.presentation.feature.onboard.signup.SignupBViewModel.Companion.CAN_NOT_SIGN_UP
import com.bestswlkh0310.presentation.feature.onboard.signup.SignupBViewModel.Companion.SIGN_UP
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupBFragment: BaseFragment<FragmentSignupBBinding, SignupBViewModel>() {
    override val viewModel: SignupBViewModel by viewModels()

    private val args by navArgs<SignupBFragmentArgs>()

    override fun observerViewModel() {
        with(viewModel) {
            nickName.value = args.nickName
            pw.value = args.pw
            bjId.value = args.bjId
        }
        bindingViewEvent { event ->
            when (event) {
                SIGN_UP -> findNavController().popBackStack(R.id.startFragment, false)
                CAN_NOT_SIGN_UP -> showToast("해당 백준 아이디혹은 닉네임으로 가입된 계정이 이미 있습니다")
            }
        }
    }
}