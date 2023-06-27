package com.bestswlkh0310.presentation.feature.onboard.signup

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentSignupABinding
import com.bestswlkh0310.presentation.feature.onboard.signup.SignupAViewModel.Companion.FOUND_BJ_ID
import com.bestswlkh0310.presentation.feature.onboard.signup.SignupAViewModel.Companion.NOT_FOUND_BJ_ID
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
            when (event) {
                NOT_FOUND_BJ_ID -> Toast.makeText(activity, "백준 아이디를 다시 입력해주세요", Toast.LENGTH_SHORT).show()
                FOUND_BJ_ID -> findNavController().navigate(SignupAFragmentDirections.actionSignupAFragmentToSignupBFragment(
                    viewModel.nickName.value!!,
                    viewModel.bjId.value!!
                ))
            }
        }
    }
}