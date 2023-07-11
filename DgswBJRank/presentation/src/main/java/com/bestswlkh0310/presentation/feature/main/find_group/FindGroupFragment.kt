package com.bestswlkh0310.presentation.feature.main.find_group

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bestswlkh0310.domain.entity.GroupModel
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentFindGroupBinding
import com.bestswlkh0310.presentation.feature.main.find_group.FindGroupViewModel.Companion.ON_CLICK_BACK
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindGroupFragment: BaseFragment<FragmentFindGroupBinding, FindGroupViewModel>() {
    override val viewModel: FindGroupViewModel by viewModels()
    private lateinit var adapter: FindGroupAdapter

    private val groupList = arrayListOf<GroupModel>()

    override fun observerViewModel() {
        bindingViewEvent { event ->
            when (event) {
                ON_CLICK_BACK -> findNavController().popBackStack()
            }
        }

        with(viewModel) {

        }
    }

    override fun onStart() {
        super.onStart()
        with(mBinding) {
            adapter = FindGroupAdapter(groupList)
            rvFindGroup.adapter = adapter
            rvFindGroup.layoutManager = LinearLayoutManager(activity)
        }
    }
}