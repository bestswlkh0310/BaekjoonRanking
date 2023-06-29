package com.bestswlkh0310.presentation.feature.main.profile

import androidx.fragment.app.viewModels
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentHomeBinding
import com.bestswlkh0310.presentation.databinding.FragmentProfileBinding

class ProfileFragment: BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override val viewModel: ProfileViewModel by viewModels()
    override fun observerViewModel() {

    }
}