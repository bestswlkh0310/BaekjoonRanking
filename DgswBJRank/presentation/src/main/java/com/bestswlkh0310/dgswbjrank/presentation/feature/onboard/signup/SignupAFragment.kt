package com.bestswlkh0310.dgswbjrank.presentation.feature.onboard.signup

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.databinding.FragmentSignupABinding
import com.bestswlkh0310.dgswbjrank.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupAFragment: BaseFragment<FragmentSignupABinding, SignupAViewModel>() {
    override val viewModel: SignupAViewModel by viewModels()
    override fun observerViewModel() {
        with(viewModel) {
            nickName.observe(this@SignupAFragment) {
                nickNameState.value = (it != "" || bjIdState.value!!)
            }
            bjId.observe(this@SignupAFragment) {
                bjIdState.value = it != ""
            }
        }
    }

    override fun onStart() {
        super.onStart()
        initButtonListener()
    }

    private fun initButtonListener() {
        with(mBinding) {
            btnNext.setOnClickListener {
                try {
                    viewModel.checkBjId(viewModel.bjId.value!!) {
                        val action = SignupAFragmentDirections.actionSignupAFragmentToSignupBFragment(viewModel.nickName.value!!, viewModel.bjId.value!!)
                        findNavController().navigate(action)
                    }
                } catch (e: Exception) {
                    Toast.makeText(activity, "백준 아이디를 다시 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}