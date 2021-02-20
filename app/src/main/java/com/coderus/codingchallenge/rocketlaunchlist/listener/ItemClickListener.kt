package com.coderus.codingchallenge.rocketlaunchlist.listener

import com.coderus.codingchallenge.domain.RocketLaunch

interface ItemClickListener {
    fun onRocketClick(rocketLaunch: RocketLaunch)
}