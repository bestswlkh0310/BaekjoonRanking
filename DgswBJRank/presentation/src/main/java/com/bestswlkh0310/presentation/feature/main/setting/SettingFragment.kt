package com.bestswlkh0310.presentation.feature.main.setting

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentSettingBinding
import com.bestswlkh0310.presentation.feature.main.setting.SettingViewModel.Companion.ON_CLICK_BACK
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment: BaseFragment<FragmentSettingBinding, SettingViewModel>() {
    override val viewModel: SettingViewModel by viewModels()
    override fun observerViewModel() {
        bindingViewEvent {  event ->
            when (event) {
                ON_CLICK_BACK -> findNavController().popBackStack()
            }
        }
    }
}