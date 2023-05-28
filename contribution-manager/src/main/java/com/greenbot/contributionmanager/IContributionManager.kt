package com.greenbot.contributionmanager

import com.greenbot.contributorsapi.IBottomNavigationContribution

interface IContributionManager {

    fun getBottomNavigationContribution(): List<IBottomNavigationContribution>
}