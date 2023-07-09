package com.bestswlkh0310.presentation.feature.main.home.group

import androidx.fragment.app.viewModels
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentGroupBinding

class GroupFragment: BaseFragment<FragmentGroupBinding, GroupViewModel>() {
    override val viewModel: GroupViewModel by viewModels()

    override fun observerViewModel() {
        
    }
}