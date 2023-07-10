package com.bestswlkh0310.presentation.feature.main.home.friend

import androidx.fragment.app.viewModels
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentFriendBinding

class FriendFragment: BaseFragment<FragmentFriendBinding, FriendViewModel>() {
    override val viewModel: FriendViewModel by viewModels()

    override fun observerViewModel() {

    }
}