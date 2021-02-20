package com.coderus.codingchallenge.viewmodel

import android.net.ConnectivityManager
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.coderus.codingchallenge.App
import com.coderus.codingchallenge.network.api.APIService
import com.coderus.codingchallenge.network.domain.RocketLaunchJson
import com.coderus.codingchallenge.repository.RocketLaunchRepository
import com.coderus.codingchallenge.rocketlaunchlist.ListViewModel
import com.coderus.codingchallenge.utils.TestCoroutineRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ListViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository: RocketLaunchRepository

    @Mock
    private lateinit var connectivityManager: ConnectivityManager

    private lateinit var app: App

    private lateinit var viewModel: ListViewModel

//    val apiService: APIService = mock(APIService::class.java)
//    var repository: RocketLaunchRepository = mock(RocketLaunchRepository::class.java)
//    var dao: Dao = mock(Dao::class.java)
//    val database: RocketDatabase = mock(RocketDatabase::class.java)
//    private val connectionChecker: ConnectionChecker = mock(ConnectionChecker::class.java)


    private val rocketLaunchList = listOf(
        RocketLaunchJson(1, "F20", "3-4-2020", "Is good", true, false),
        RocketLaunchJson(1, "F40", "3-5-2020", "Is good", true, false),
        RocketLaunchJson(1, "F60", "3-6-2020", "Is good", true, false)
    )

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
         app = App()
        viewModel = ListViewModel(app, repository)
    }

    @Test
    fun testSomeThing() {
        testCoroutineRule.runBlockingTest {
            val api = mock<APIService> {

                if(app.hasInternetConnection())
                onBlocking { getRocketLaunchList() } doReturn rocketLaunchList
            }

            verify(api).getRocketLaunchList()

        }

//        //given

//        //when

//        //then
    }

}


private fun <T> LiveData<T>.blockingObserve(): T? {
    var value: T? = null
    val latch = CountDownLatch(1)

    val observer = Observer<T> { t ->
        value = t
        latch.countDown()
    }

    observeForever(observer)

    latch.await(2, TimeUnit.SECONDS)
    return value
}

