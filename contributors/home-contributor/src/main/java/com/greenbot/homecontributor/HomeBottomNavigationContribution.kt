package com.greenbot.homecontributor

import androidx.fragment.app.Fragment
import com.greenbot.contributorsapi.BottomNavigationContributionState
import com.greenbot.contributorsapi.IBottomNavigationContribution

class HomeBottomNavigationContribution : IBottomNavigationContribution {
    override val state: BottomNavigationContributionState
        get() = BottomNavigationContributionState(
            text = "Home",
            icon = R.drawable.ic_home_black_24dp,
            order = 1
        )

    override fun fragment(): Class<out Fragment> = HomeFragment::class.java

    override val id: String
        get() = "home-contribution"
    override val isEnabled: Boolean
        get() = true
}