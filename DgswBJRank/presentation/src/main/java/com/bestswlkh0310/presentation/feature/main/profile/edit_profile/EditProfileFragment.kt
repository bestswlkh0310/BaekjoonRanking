package com.bestswlkh0310.presentation.feature.main.profile.edit_profile

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentEditProfileBinding
import com.bestswlkh0310.presentation.feature.main.profile.edit_profile.EditProfileViewModel.Companion.ON_CLICK_BACK
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment: BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>() {
    override val viewModel: EditProfileViewModel by viewModels()
    override fun observerViewModel() {
        bindingViewEvent {  event ->
            when (event) {
                ON_CLICK_BACK -> findNavController().popBackStack()
            }
        }
    }
}