package com.bestswlkh0310.presentation.feature.main.find_group

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bestswlkh0310.domain.entity.GroupModel
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentFindGroupBinding
import com.bestswlkh0310.presentation.feature.main.find_group.FindGroupViewModel.Companion.ALREADY_HAVE_GROUP
import com.bestswlkh0310.presentation.feature.main.find_group.FindGroupViewModel.Companion.CAN_NOT_JOIN_GROUP
import com.bestswlkh0310.presentation.feature.main.find_group.FindGroupViewModel.Companion.JOIN
import com.bestswlkh0310.presentation.feature.main.find_group.FindGroupViewModel.Companion.LOAD_GROUPS
import com.bestswlkh0310.presentation.feature.main.find_group.FindGroupViewModel.Companion.ON_CLICK_BACK
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindGroupFragment: BaseFragment<FragmentFindGroupBinding, FindGroupViewModel>() {
    override val viewModel: FindGroupViewModel by viewModels()
    private lateinit var adapter: FindGroupAdapter

    private val groupList: MutableList<GroupModel> = arrayListOf()

    override fun observerViewModel() {
        bindingViewEvent { event ->
            when (event) {
                ON_CLICK_BACK -> findNavController().popBackStack()
                LOAD_GROUPS -> adapter.notifyItemRangeInserted(0, groupList.size)
                JOIN -> {
                    // TODO : move detail fragment
                    showToast("가입이 완료되었습니다")
                }
                CAN_NOT_JOIN_GROUP -> showToast("그룹에 가입할 수 없습니다")
                ALREADY_HAVE_GROUP -> showToast("이미 그룹에 가입되어있습니다")
            }
        }

        with(viewModel) {

        }
    }

    override fun onStart() {
        super.onStart()
        with(mBinding) {
            adapter = FindGroupAdapter(groupList) { position ->
                val group = groupList[position]
                viewModel.join(group)
            }
            rvFindGroup.adapter = adapter
            rvFindGroup.layoutManager = LinearLayoutManager(activity)
        }
        viewModel.initGroups(groupList)
    }
}