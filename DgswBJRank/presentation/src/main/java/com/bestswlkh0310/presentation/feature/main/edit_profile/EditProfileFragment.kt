package com.bestswlkh0310.presentation.feature.main.edit_profile

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentEditProfileBinding
import com.bestswlkh0310.presentation.feature.main.edit_profile.EditProfileViewModel.Companion.ALREADY_EXIST_BJ_ID
import com.bestswlkh0310.presentation.feature.main.edit_profile.EditProfileViewModel.Companion.NOT_VALID_BJ_ID
import com.bestswlkh0310.presentation.feature.main.edit_profile.EditProfileViewModel.Companion.ON_CLICK_BACK
import com.bestswlkh0310.presentation.feature.main.edit_profile.EditProfileViewModel.Companion.COMPLETE
import com.bestswlkh0310.presentation.feature.main.edit_profile.EditProfileViewModel.Companion.VALID_BJ_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment: BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>() {
    override val viewModel: EditProfileViewModel by viewModels()
    override fun observerViewModel() {
        bindingViewEvent {  event ->
            when (event) {
                ON_CLICK_BACK -> findNavController().popBackStack()
                COMPLETE -> findNavController().popBackStack()
                VALID_BJ_ID -> showToast("사용 가능한 백준 아이디")
                NOT_VALID_BJ_ID -> showToast("사용 불가능한 백준 아이디")
                ALREADY_EXIST_BJ_ID -> showToast("이미 사용 중인 백준 아이디")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.initProfile()
    }
}