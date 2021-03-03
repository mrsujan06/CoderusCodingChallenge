package com.coderus.codingchallenge.rocketlaunchlist

import com.coderus.codingchallenge.domain.RocketLaunch

interface ItemClickListener {
    fun onRocketClick(rocketLaunch: RocketLaunch)
}