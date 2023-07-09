package com.bestswlkh0310.presentation.feature.main.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bestswlkh0310.presentation.R
import com.bestswlkh0310.presentation.base.BaseFragment
import com.bestswlkh0310.presentation.databinding.FragmentHomeBinding
import com.bestswlkh0310.presentation.feature.main.home.friend.FriendFragment
import com.bestswlkh0310.presentation.feature.main.home.group.GroupFragment
import com.bestswlkh0310.presentation.feature.main.profile.ProfileFragment
import com.bestswlkh0310.presentation.feature.main.rank.RankFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override fun observerViewModel() {

    }

    override fun onStart() {
        super.onStart()
        initFriendPager()
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