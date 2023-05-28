package com.greenbot.settingscontributor

import com.greenbot.contributorsapi.IBottomNavigationContribution
import com.greenbot.contributorsapi.IContributor

class SettingsContributor : IContributor {
    override val id: String
        get() = "settings-contributor"

    override fun bottomNavigationContribution(): IBottomNavigationContribution =
        SettingsBottomNavigationContribution()
}