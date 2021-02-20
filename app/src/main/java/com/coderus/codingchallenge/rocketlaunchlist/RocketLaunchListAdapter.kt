package com.coderus.codingchallenge.rocketlaunchlist

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coderus.codingchallenge.domain.RocketLaunch
import com.coderus.codingchallenge.rocketlaunchlist.listener.ItemClickListener
import com.coderus.codingchallenge.network.domain.RocketLaunchJson

/**
 * ListAdapter class for the Recycler view that displays a list of [RocketLaunchJson] objects.
 */
class RocketLaunchListAdapter internal constructor(
    private val context: Context,
    private val itemClickListener: ItemClickListener
) :
    ListAdapter<RocketLaunch, RocketLaunchListAdapter.ListItemViewHolder>(
        RocketLaunchListDiffCallback()
    ) {

    inner class ListItemViewHolder(val listItemView: ListItemView) :
        RecyclerView.ViewHolder(listItemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(ListItemView(context))
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.listItemView.setItem(item)
        holder.itemView.setOnClickListener {
            itemClickListener.onRocketClick(item)
        }
    }


}
