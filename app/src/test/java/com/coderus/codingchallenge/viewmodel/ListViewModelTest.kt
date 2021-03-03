package com.coderus.codingchallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.coderus.codingchallenge.database.RocketEntities
import com.coderus.codingchallenge.repository.RocketLaunchRepository
import com.coderus.codingchallenge.rocketlaunchlist.ListViewModel
import com.coderus.codingchallenge.rocketlaunchlist.ListViewModel.State
import com.coderus.codingchallenge.utils.TestCoroutineRule
import com.nhaarman.mockitokotlin2.given
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ListViewModelTest {

    private lateinit var viewModel: ListViewModel

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository: RocketLaunchRepository

    private val rocketLaunchList = listOf(
        RocketEntities(1, "F20", "3-4-2020", "Is good", true, false),
        RocketEntities(1, "F40", "3-5-2020", "Is good", true, false),
        RocketEntities(1, "F60", "3-6-2020", "Is good", true, false)
    )

    @Before
    fun setUp() {

    }

    @Test
    fun test_fetch_rocket_launch_list_from_db() =
        testCoroutineRule.runBlockingTest {
            given(repository.fetchRocketLaunchList()).willReturn(rocketLaunchList)
            viewModel = ListViewModel(repository)
            val value = State.Success(rocketLaunchList).rocketLaunchList
            TestCase.assertNotNull(value)
            assertEquals(value, rocketLaunchList)
        }


    @Test
    fun test_network_error_while_fetching() =
        testCoroutineRule.runBlockingTest {
            given(repository.fetchRocketLaunchList()).willReturn(rocketLaunchList)
            viewModel = ListViewModel(repository)
            val value = State.Error(IOException(), rocketLaunchList).error.localizedMessage
            assertEquals(value, IOException().localizedMessage)
        }
}

