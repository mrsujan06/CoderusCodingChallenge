package com.coderus.codingchallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.coderus.codingchallenge.database.RocketEntities
import com.coderus.codingchallenge.rocketlaunchlist.ListViewModel
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
            given(repository.fetchRocketLaunchList()).willReturn(flowOf(rocketLaunchList))
            viewModel = ListViewModel(repository)
            TestCase.assertNotNull(viewModel.rocketLaunch)
            viewModel.rocketLaunch.observeForever {}
            assertEquals(viewModel.rocketLaunch.value, rocketLaunchList)
        }
}

