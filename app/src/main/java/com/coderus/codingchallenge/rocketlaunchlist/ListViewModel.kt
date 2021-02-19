package com.coderus.codingchallenge.rocketlaunchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coderus.codingchallenge.repository.RocketLaunchRepository
import com.coderus.codingchallenge.utils.ConnectionChecker
import kotlinx.coroutines.launch
import okio.IOException
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel class to expose data to the ListFragment that it is required to display.
 */
class ListViewModel @Inject constructor(
    private val repository: RocketLaunchRepository,
    private val connectionChecker: ConnectionChecker
) :
    ViewModel() {

    // list of RocketLaunch domain data
    val rocketLaunches = repository.fetchRocketLaunches()

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    /**
     *  Refresh data in the Repository
     * */
    fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                if (!connectionChecker.isOnline()) {
                    _loadingState.value = LoadingState.ERROR
                } else {
                    _loadingState.value = LoadingState.LOADING
                    repository.refreshRocketLaunches().also {
                        _loadingState.value = LoadingState.DONE
                    }
                }

            } catch (networkError: IOException) {
                if (rocketLaunches.value.isNullOrEmpty()) {
                    _loadingState.value = LoadingState.ERROR
                }
                Timber.e(networkError.localizedMessage)
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
