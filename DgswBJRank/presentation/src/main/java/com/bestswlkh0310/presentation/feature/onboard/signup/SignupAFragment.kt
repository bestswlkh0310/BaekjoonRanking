package com.bestswlkh0310.presentation.feature.onboard.signup

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentSignupABinding
import com.bestswlkh0310.presentation.feature.onboard.signup.SignupAViewModel.Companion.FOUND_BJ_ID
import com.bestswlkh0310.presentation.feature.onboard.signup.SignupAViewModel.Companion.NOT_FOUND_BJ_ID
import com.bestswlkh0310.presentation.util.Constant.TAAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupAFragment: BaseFragment<FragmentSignupABinding, SignupAViewModel>() {
    override val viewModel: SignupAViewModel by viewModels()
    override fun observerViewModel() {
        with(viewModel) {
            nickName.observe(this@SignupAFragment) {
                nickNameState.value = (it != "" || bjIdState.value!! || pwState.value!!)
            }
            pw.observe(this@SignupAFragment) {
                pwState.value = (it != "" || bjIdState.value!!)
            }
            bjId.observe(this@SignupAFragment) {
                bjIdState.value = it != ""
                bjIdDetail.value = if (bjId.value != "") {
                    "그런 아이디는 없어요"
                } else {
                    ""
                }
            }
        }

        bindingViewEvent { event ->
            Log.d(TAAG, "12345678 - observerViewModel() called")
            when (event) {
                NOT_FOUND_BJ_ID -> showToast("백준 아이디를 다시 입력해주세요")
                FOUND_BJ_ID -> {
                    Log.d(TAAG, "SignupAFragment - observerViewModel() called")
                    findNavController().navigate(SignupAFragmentDirections.actionSignupAFragmentToSignupBFragment(
                        viewModel.bjId.value!!,
                        viewModel.nickName.value!!,
                        viewModel.pw.value!!
                    ))
                }
            }
        }
    }
}