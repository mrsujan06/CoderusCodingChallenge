package com.coderus.codingchallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.coderus.codingchallenge.database.RocketEntities
import com.coderus.codingchallenge.network.domain.RocketLaunchJson
import com.coderus.codingchallenge.repository.RocketLaunchRepository
import com.coderus.codingchallenge.rocketlaunchlist.viewmodel.ListViewModel
import com.coderus.codingchallenge.utils.Resource
import com.coderus.codingchallenge.utils.TestCoroutineRule
import com.nhaarman.mockitokotlin2.given
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ListViewModelTest {

    lateinit var viewModel: ListViewModel

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<List<RocketEntities>>>

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository: RocketLaunchRepository

    private val rocketLaunchList = listOf(
        RocketEntities(1, "F20", "3-4-2020", "Is good", true, false),
        RocketEntities(1, "F40", "3-5-2020", "Is good", true, false),
        RocketEntities(1, "F60", "3-6-2020", "Is good", true, false)
    )

    private val rocketLaunchListNetwork = listOf(
        RocketLaunchJson(1, "F20", "3-4-2020", "Is good", true, false),
        RocketLaunchJson(1, "F40", "3-5-2020", "Is good", true, false),
        RocketLaunchJson(1, "F60", "3-6-2020", "Is good", true, false)
    )

    @Before
    fun setup() {
//        given(repository.rocketLaunch).willReturn(flowOf(rocketLaunchList))
//        viewModel = ListViewModel(repository)
    }

    @Test
    fun test_fetch_rocket_launch_list_from_db() =
        testCoroutineRule.runBlockingTest {
            TestCase.assertNotNull(viewModel.rocketLaunch)
            viewModel.rocketLaunch.observeForever { observer }
            assertEquals(viewModel.rocketLaunch.value, rocketLaunchList)
            viewModel.rocketLaunch.removeObserver { observer }
        }

//    @Test
//    fun test_fetch_rocket_launch_list_from_network() =
//        testCoroutineRule.runBlockingTest {
//            given(repository.fetchRocketLaunchesFromNetwork()).willReturn(rocketLaunchListNetwork)
//            viewModel = ListViewModel(repository)
//            assertEquals(viewModel.rockerLaunchFromNet.value, rocketLaunchListNetwork)
//        }

}
