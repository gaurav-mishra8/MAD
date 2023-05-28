package com.greenbot.contributorsapi

interface IContributor {
    val id: String

    fun bottomNavigationContribution(): IBottomNavigationContribution
}