package com.bestswlkh0310.presentation.feature.onboard.sign_in

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.presentation.R
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentSignInBinding
import com.bestswlkh0310.presentation.feature.onboard.OnBoardActivity
import com.bestswlkh0310.presentation.feature.onboard.sign_in.SignInViewModel.Companion.CHECK_ALARM_PERMISSION
import com.bestswlkh0310.presentation.feature.onboard.sign_in.SignInViewModel.Companion.NOT_FOUND_USER
import com.bestswlkh0310.presentation.feature.onboard.sign_in.SignInViewModel.Companion.ON_CLICK_BACK
import com.bestswlkh0310.presentation.feature.onboard.sign_in.SignInViewModel.Companion.ON_CLICK_SIGN_UP
import com.bestswlkh0310.presentation.feature.onboard.sign_in.SignInViewModel.Companion.WRONG_INPUT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment: BaseFragment<FragmentSignInBinding, SignInViewModel>() {
    override val viewModel: SignInViewModel by viewModels()

    private val registerForActivityResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            when (it) {
                true -> {
                }
                false -> {
                }
        }
        callback()
    }
    private lateinit var callback: () -> Unit

    override fun observerViewModel() {

        bindingViewEvent {
            when (it) {
                NOT_FOUND_USER -> showToast("올바른 아이디 비밀 번호를 입력해 주세요")
                WRONG_INPUT -> showToast("아이디 비밀 번호를 입력해 주세요")
                CHECK_ALARM_PERMISSION -> checkAlarmPermission {
                    if (activity is OnBoardActivity) (activity as OnBoardActivity).startMainActivity()
                }
                ON_CLICK_BACK -> findNavController().popBackStack()
                ON_CLICK_SIGN_UP -> findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
            }
        }
    }

    private fun checkAlarmPermission(callback: () -> Unit) {
        this.callback = callback
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.POST_NOTIFICATIONS)) {
                    // 이미 권한을 거절한 경우 권한 설정 화면으로 이동
                    val intent: Intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(
                        Uri.parse("package:" + requireActivity().packageName))
                    startActivity(intent)
                    requireActivity().finish()
                } else {
                    // 처음 권한 요청을 할 경우
                    registerForActivityResult.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            } else {
                callback()
            }
        }
    }
}