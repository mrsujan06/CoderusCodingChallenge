package com.coderus.codingchallenge.rocketlaunchlist

import androidx.lifecycle.*
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
) : ViewModel() {

    //list of RocketLaunch domain data
    val rocketLaunch = repository.fetchRocketLaunchList().asLiveData()

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    init {
        refreshRocketLaunchDb()
    }

    /**
     *  Refresh data in the Repository
     * */
    private fun refreshRocketLaunchDb() {
        viewModelScope.launch {
            _loadingState.value = LoadingState.LOADING
            try {
                if (connectionChecker.isOnline()) {
                    repository.refreshRocketLaunchDb().also {
                        _loadingState.value = LoadingState.DONE
                    }
                } else {
                    _loadingState.value = LoadingState.ERROR
                }
            } catch (networkError: IOException) {
                Timber.e(networkError.localizedMessage)
                if (rocketLaunch.value.isNullOrEmpty()) {
                    _loadingState.value = LoadingState.ERROR
                }
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
