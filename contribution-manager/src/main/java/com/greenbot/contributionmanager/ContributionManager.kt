package com.greenbot.contributionmanager

import com.greenbot.contributorsapi.IBottomNavigationContribution

class ContributionManager(
    private val registry: IContributorRegistry
) : IContributionManager {
    override fun getBottomNavigationContribution(): List<IBottomNavigationContribution> {
        return registry.registeredContributors().map {
            it.bottomNavigationContribution()
        }.filter {
            it.isEnabled
        }.sortedBy {
            it.state.order
        }
    }
}