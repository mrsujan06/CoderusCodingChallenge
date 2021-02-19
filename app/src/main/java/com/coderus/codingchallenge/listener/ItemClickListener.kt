package com.coderus.codingchallenge.listener

import com.coderus.codingchallenge.domain.RocketLaunch

interface ItemClickListener {
    fun onRocketClick(rocketLaunch: RocketLaunch)
}