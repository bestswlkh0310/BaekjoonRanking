package com.bestswlkh0310.presentation.feature.main.group

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.presentation.R
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentGroupBinding
import com.bestswlkh0310.presentation.feature.main.group.GroupViewModel.Companion.ON_CLICK_CREATE_GROUP
import com.bestswlkh0310.presentation.feature.main.group.GroupViewModel.Companion.ON_CLICK_FIND_GROUP
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupFragment: BaseFragment<FragmentGroupBinding, GroupViewModel>() {
    override val viewModel: GroupViewModel by viewModels()
    override val hasBottomNav = true

    override fun observerViewModel() {
        bindingViewEvent { event ->
            when (event) {
                ON_CLICK_FIND_GROUP -> findNavController().navigate(R.id.action_homeFragment_to_findGroupFragment)
                ON_CLICK_CREATE_GROUP -> findNavController().navigate(R.id.action_homeFragment_to_createGroupFragment)
            }
        }
    }
}