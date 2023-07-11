package com.bestswlkh0310.presentation.feature.main.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentHomeBinding
import com.bestswlkh0310.presentation.feature.main.home.HomeViewModel.Companion.NOT_FOUND_BJ_ID
import com.bestswlkh0310.presentation.feature.main.home.friend.FriendFragment
import com.bestswlkh0310.presentation.feature.main.home.group.GroupFragment
import com.bestswlkh0310.presentation.util.Common.getTodayDate
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val hasBottomNav = true
    override val viewModel: HomeViewModel by viewModels()
    override fun observerViewModel() {
        with(viewModel) {
            point.observe(this@HomeFragment) { mBinding.btnPoint.text = it.toString() + "P" }
            solve.observe(this@HomeFragment) { mBinding.solve.text = it.toString() + " 문제" }
        }

        bindingViewEvent {  event ->
            when (event) {
                NOT_FOUND_BJ_ID -> mBinding.solve.text = "백준 아이디가 없어요!"
            }
        }
    }

    override fun onStart() {
        super.onStart()
        initFriendPager()
        viewModel.initPoint()
        viewModel.initSolve()
        initDate()
    }

    private fun initDate() {
        val (year, month, day) = LocalDate.now().toString().split('-').map { it.replace("0", "") }
        val dayOfWeek = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale("ko", "KR"))[0]
        mBinding.date.text = "${month}. ${day}. (${dayOfWeek})"
    }

    private fun initFriendPager() {

        val viewPager = mBinding.viewPager
        val tabLayout = mBinding.tabLayout

        val adapter = FriendPagerAdapter(this)
        adapter.addFragment(FriendFragment(), "친구")
        adapter.addFragment(GroupFragment(), "그룹")
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val destination = adapter.createFragment(tab.position).id
                val navController = findNavController()
                navController.currentDestination?.getAction(destination)?.let {
                    navController.navigate(destination, null, it.navOptions)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}