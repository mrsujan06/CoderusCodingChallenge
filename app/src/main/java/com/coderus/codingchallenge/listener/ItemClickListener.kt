package com.coderus.codingchallenge.listener

import com.coderus.codingchallenge.model.RocketLaunch

interface ItemClickListener {
    fun onRocketClick(rocketLaunch: RocketLaunch)
}