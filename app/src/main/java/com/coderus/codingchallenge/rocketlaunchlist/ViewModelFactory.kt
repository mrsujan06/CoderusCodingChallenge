package com.coderus.codingchallenge.rocketlaunchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coderus.codingchallenge.repository.RocketLaunchRepository
import javax.inject.Inject

/**
 * ViewModelFactory to construct the view models required.
 */
class ViewModelFactory @Inject constructor(
    private val rocketLaunchRepository: RocketLaunchRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(rocketLaunchRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
