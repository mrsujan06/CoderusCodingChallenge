package com.coderus.codingchallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coderus.codingchallenge.repository.RocketLaunchRepository
import com.coderus.codingchallenge.rocketlaunchlist.ListViewModel
import com.coderus.codingchallenge.utils.ConnectionChecker
import javax.inject.Inject

/**
 * ViewModelFactory to construct the view models required.
 */
class ViewModelFactory @Inject constructor(
    private val launchRepository: RocketLaunchRepository,
    private val connectionChecker: ConnectionChecker
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(launchRepository,connectionChecker) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
