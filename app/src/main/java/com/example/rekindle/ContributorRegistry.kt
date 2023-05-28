package com.example.rekindle

import com.greenbot.contributionmanager.IContributorRegistry
import com.greenbot.contributorsapi.IContributor
import com.greenbot.homecontributor.HomeContributor
import com.greenbot.settingscontributor.SettingsContributor

class ContributorRegistry : IContributorRegistry {
    override fun registeredContributors(): List<IContributor> {
        return listOf(HomeContributor(), SettingsContributor())
    }
}