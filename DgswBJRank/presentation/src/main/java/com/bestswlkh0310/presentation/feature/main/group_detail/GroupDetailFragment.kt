package com.bestswlkh0310.presentation.feature.main.group_detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentFindGroupBinding
import com.bestswlkh0310.presentation.feature.main.group_detail.GroupDetailViewModel.Companion.ON_CLICK_BACK
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupDetailFragment: BaseFragment<FragmentFindGroupBinding, GroupDetailViewModel>() {
    override val viewModel: GroupDetailViewModel by viewModels()
    override fun observerViewModel() {
        bindingViewEvent {  event ->
            when (event) {
                ON_CLICK_BACK -> findNavController().popBackStack()
            }
        }
    }
}