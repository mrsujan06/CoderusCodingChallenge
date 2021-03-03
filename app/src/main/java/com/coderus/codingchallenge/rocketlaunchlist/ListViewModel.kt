package com.coderus.codingchallenge.rocketlaunchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coderus.codingchallenge.database.RocketEntities
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

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    init {
        refreshRocketLaunchDb()
    }

    /**
     *  Refresh data in Database
     * */
    private fun refreshRocketLaunchDb() {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                repository.refreshRocketLaunchDb().also {
                    _state.value = State.Success(repository.fetchRocketLaunchList())
                }
            } catch (networkError: IOException) {
                Timber.e(networkError.localizedMessage)
                _state.value = State.Error(networkError, repository.fetchRocketLaunchList())
            }
        }
    }


    sealed class State {
        object Loading : State()
        data class Success(val rocketLaunchList: List<RocketEntities>) : State()
        data class Error(val error: Exception, val rocketLaunchList: List<RocketEntities>) : State()
    }
}
