package com.greenbot.contributionmanager

import com.greenbot.contributorsapi.IContributor

interface IContributorRegistry {

    fun registeredContributors(): List<IContributor>
}