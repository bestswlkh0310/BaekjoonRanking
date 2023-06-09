package com.bestswlkh0310.presentation.feature.main.profile

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.presentation.R
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentProfileBinding
import com.bestswlkh0310.presentation.feature.main.profile.ProfileViewModel.Companion.ON_CLICK_EDIT_PROFILE
import com.bestswlkh0310.presentation.feature.main.profile.ProfileViewModel.Companion.ON_CLICK_SETTING
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override val viewModel: ProfileViewModel by viewModels()
    override val hasBottomNav = true

    override fun observerViewModel() {
        bindingViewEvent {  event ->
            when (event) {
                ON_CLICK_EDIT_PROFILE -> findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
                ON_CLICK_SETTING -> findNavController().navigate(R.id.action_profileFragment_to_settingFragment)
            }
        }

        with(viewModel) {
            point.observe(this@ProfileFragment) { mBinding.pointValue.text = it.toString() + "P" }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.initPoint()
        viewModel.initProfile()
    }
}
