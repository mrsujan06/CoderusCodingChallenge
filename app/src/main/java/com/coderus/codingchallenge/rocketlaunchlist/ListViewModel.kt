package com.coderus.codingchallenge.rocketlaunchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coderus.codingchallenge.model.RocketLaunch
import com.coderus.codingchallenge.repository.RocketLaunchesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel class to expose data to the ListFragment that it is required to display.
 */
class ListViewModel @Inject constructor(private val repository: RocketLaunchesRepository) :
    ViewModel() {

    private var _rocketLunches: MutableLiveData<List<RocketLaunch>> = MutableLiveData()
    val rocketLunches: LiveData<List<RocketLaunch>>
        get() = _rocketLunches

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    /**
     * Request the list of [RocketLaunch] data.
     */
    fun retrieveData() {
        viewModelScope.launch {
            _loadingState.value = LoadingState.LOADING
            repository.fetchRocketLaunches()
                .handleErrors()
                .collect {
                    _rocketLunches.value = it
                    _loadingState.value = LoadingState.DONE
                }
        }
    }

    enum class LoadingState {
        LOADING,
        DONE,
        ERROR
    }

    private fun <T> Flow<T>.handleErrors(): Flow<T> = flow {
        try {
            collect { value -> emit(value) }
        } catch (e: Throwable) {
            _loadingState.value = LoadingState.ERROR
            Timber.e(e.localizedMessage)
        }
    }
}
