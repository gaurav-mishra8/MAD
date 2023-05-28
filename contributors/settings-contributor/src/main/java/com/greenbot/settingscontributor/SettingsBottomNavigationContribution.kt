package com.greenbot.settingscontributor

import androidx.fragment.app.Fragment
import com.greenbot.contributorsapi.BottomNavigationContributionState
import com.greenbot.contributorsapi.IBottomNavigationContribution

class SettingsBottomNavigationContribution : IBottomNavigationContribution {
    override val state: BottomNavigationContributionState
        get() = BottomNavigationContributionState(
            text = "settings",
            icon = R.drawable.ic_notifications_black_24dp,
            order = 2
        )

    override fun fragment(): Class<out Fragment> = SettingsFragment::class.java

    override val id: String
        get() = "settings-contribution"
    override val isEnabled: Boolean
        get() = true
}