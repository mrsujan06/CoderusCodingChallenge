package com.coderus.codingchallenge.rocketlaunchlist

import androidx.recyclerview.widget.DiffUtil
import com.coderus.codingchallenge.model.RocketLaunch

/**
 * [DiffUtil.ItemCallback] for the [RocketLaunch] list.
 */
class RocketLaunchListDiffCallback : DiffUtil.ItemCallback<RocketLaunch>() {

    override fun areItemsTheSame(oldItem: RocketLaunch, newItem: RocketLaunch) =
        oldItem.flightNumber == newItem.flightNumber

    override fun areContentsTheSame(oldItem: RocketLaunch, newItem: RocketLaunch) =
        oldItem == newItem

}
