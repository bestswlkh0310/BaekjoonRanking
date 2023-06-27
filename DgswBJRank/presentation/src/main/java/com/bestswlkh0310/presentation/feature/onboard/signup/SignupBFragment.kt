package com.bestswlkh0310.presentation.feature.onboard.signup

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bestswlkh0310.presentation.R
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentSignupBBinding
import com.bestswlkh0310.presentation.feature.onboard.signup.SignupBViewModel.Companion.SIGN_UP
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupBFragment: BaseFragment<FragmentSignupBBinding, SignupBViewModel>() {
    override val viewModel: SignupBViewModel by viewModels()

    private val args by navArgs<SignupBFragmentArgs>()

    override fun observerViewModel() {
        with(viewModel) {
            nickname.value = args.nickName
            pw.value = args.pw
            bjId.value = args.bjId
        }
        bindingViewEvent { event ->
            when (event) {
                SIGN_UP -> findNavController().popBackStack(R.id.startFragment, false)
            }
        }
    }
}