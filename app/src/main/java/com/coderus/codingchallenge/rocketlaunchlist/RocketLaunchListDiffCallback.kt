package com.coderus.codingchallenge.rocketlaunchlist

import androidx.recyclerview.widget.DiffUtil
import com.coderus.codingchallenge.domain.RocketLaunch
import com.coderus.codingchallenge.network.dto.RocketLaunchNetwork

/**
 * [DiffUtil.ItemCallback] for the [RocketLaunchNetwork] list.
 */
class RocketLaunchListDiffCallback : DiffUtil.ItemCallback<RocketLaunch>() {

    override fun areItemsTheSame(oldItem: RocketLaunch, newItem: RocketLaunch): Boolean =
        oldItem.flightNumber == newItem.flightNumber

    override fun areContentsTheSame(oldItem: RocketLaunch, newItem: RocketLaunch) =
        oldItem == newItem

}
