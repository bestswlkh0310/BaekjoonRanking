package com.bestswlkh0310.presentation.feature.main.profile

import androidx.fragment.app.viewModels
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentHomeBinding
import com.bestswlkh0310.presentation.databinding.FragmentProfileBinding
import com.bestswlkh0310.presentation.feature.main.profile.ProfileViewModel.Companion.ON_CLICK_EDIT_PROFILE
import com.bestswlkh0310.presentation.feature.main.profile.ProfileViewModel.Companion.ON_CLICK_SETTING
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override val viewModel: ProfileViewModel by viewModels()
    override fun observerViewModel() {
        bindingViewEvent {  event ->
            when (event) {
                ON_CLICK_SETTING -> showToast("setting")
                ON_CLICK_EDIT_PROFILE -> showToast("edit profile")
            }
        }
    }
}