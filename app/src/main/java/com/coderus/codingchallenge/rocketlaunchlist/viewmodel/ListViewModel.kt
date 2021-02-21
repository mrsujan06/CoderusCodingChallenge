package com.coderus.codingchallenge.rocketlaunchlist.viewmodel

import androidx.lifecycle.*
import com.coderus.codingchallenge.repository.RocketLaunchRepository
import kotlinx.coroutines.launch
import okio.IOException
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel class to expose data to the ListFragment that it is required to display.
 */
class ListViewModel @Inject constructor(
    private val repository: RocketLaunchRepository
) : ViewModel() {

    //list of RocketLaunch data
    val rocketLaunch = repository.fetchRocketLaunchList().asLiveData()

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    init {
        refreshRocketLaunchDb()
    }

    /**
     *  Refresh data in Database
     * */
    private fun refreshRocketLaunchDb() {
        viewModelScope.launch {
            _loadingState.value = LoadingState.LOADING
            try {
                repository.refreshRocketLaunchDb().also {
                    _loadingState.value = LoadingState.DONE
                }
            } catch (networkError: IOException) {
                Timber.e(networkError.localizedMessage)
                _loadingState.value = LoadingState.ERROR
                _loadingState.value = LoadingState.DONE
            }
        }
    }

    /**
     * Enum class for Loading state
     * */
    enum class LoadingState {
        LOADING,
        DONE,
        ERROR
    }
}
