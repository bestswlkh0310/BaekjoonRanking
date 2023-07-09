package com.bestswlkh0310.presentation.feature.main.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FriendPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val fragmentList = mutableListOf<Fragment>()
    private val titleList = mutableListOf<String>()

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun getPageTitle(position: Int): String {
        return titleList[position]
    }
}
