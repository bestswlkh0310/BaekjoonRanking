package com.bestswlkh0310.presentation.feature.main.create_group

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentCreateGroupBinding
import com.bestswlkh0310.presentation.feature.main.create_group.CreateGroupViewModel.Companion.ALREADY_EXIST_GROUP_NAME
import com.bestswlkh0310.presentation.feature.main.create_group.CreateGroupViewModel.Companion.ALREADY_HAVE_GROUP
import com.bestswlkh0310.presentation.feature.main.create_group.CreateGroupViewModel.Companion.CREATE
import com.bestswlkh0310.presentation.feature.main.create_group.CreateGroupViewModel.Companion.NEED_MORE_POINT
import com.bestswlkh0310.presentation.feature.main.create_group.CreateGroupViewModel.Companion.ON_CLICK_BACK
import com.bestswlkh0310.presentation.feature.main.create_group.CreateGroupViewModel.Companion.WRONG_GROUP_NAME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateGroupFragment: BaseFragment<FragmentCreateGroupBinding, CreateGroupViewModel>() {
    override val viewModel: CreateGroupViewModel by viewModels()
    override val hasBottomNav = false

    override fun observerViewModel() {
        bindingViewEvent { event ->
            when (event) {
                ON_CLICK_BACK -> findNavController().popBackStack()
                CREATE -> findNavController().popBackStack()
                NEED_MORE_POINT -> showToast("포인트가 부족합니다")
                ALREADY_EXIST_GROUP_NAME -> showToast("같은 이름의 그룹 이름이 이미 존재합니다")
                ALREADY_HAVE_GROUP -> showToast("가입한 그룹에서 탈퇴 후 그룹을 만들어주세요!")
                WRONG_GROUP_NAME -> showToast("그룹 이름을 입력해주세요")
            }
        }
        with(viewModel) {
            memberLimit.observe(this@CreateGroupFragment) {
                memberLimitResult.value = "${it}/20 명"
            }
        }
    }
}