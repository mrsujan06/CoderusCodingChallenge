package com.coderus.codingchallenge.rocketlaunchlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.coderus.codingchallenge.database.RocketEntities
import com.coderus.codingchallenge.repository.RocketLaunchRepository
import com.coderus.codingchallenge.utils.Resource
import javax.inject.Inject

/**
 * ViewModel class to expose data to the ListFragment that it is required to display.
 */
class ListViewModel @Inject constructor(repository: RocketLaunchRepository) : ViewModel() {
    val rocketLaunch: LiveData<Resource<List<RocketEntities>>> = repository.rocketLaunch
}
