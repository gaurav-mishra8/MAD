package com.greenbot.homecontributor

import com.greenbot.contributorsapi.IBottomNavigationContribution
import com.greenbot.contributorsapi.IContributor

class HomeContributor : IContributor {
    override val id: String
        get() = "home-contributor"

    override fun bottomNavigationContribution(): IBottomNavigationContribution =
        HomeBottomNavigationContribution()
}