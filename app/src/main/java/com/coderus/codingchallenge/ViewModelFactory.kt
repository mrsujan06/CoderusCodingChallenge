package com.coderus.codingchallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coderus.codingchallenge.repository.RocketLaunchesRepository
import com.coderus.codingchallenge.rocketlaunchlist.ListViewModel
import javax.inject.Inject

/**
 * ViewModelFactory to construct the view models required.
 */
class ViewModelFactory @Inject constructor(
    private val repository: RocketLaunchesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
