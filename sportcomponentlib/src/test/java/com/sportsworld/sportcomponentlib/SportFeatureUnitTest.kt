package com.sportsworld.sportcomponentlib

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule


@OptIn(ExperimentalCoroutinesApi::class)
class SportFeatureUnitTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SportFeatureViewModelLib

    @Before
    fun setUp() {
        viewModel = SportFeatureViewModelLib()
    }

    @Test
    fun `check list of sports has been randomised`() = runTest {
        viewModel.sharedFlowInit()
        val firstShuffle = viewModel.latestSport
        advanceUntilIdle()
        viewModel.sharedFlowInit()
        val secondShuffle = viewModel.latestSport
        advanceUntilIdle()
        assertNotEquals(firstShuffle, secondShuffle)
    }


    @Test
    fun `check default list of sports is one Sport object`() = runTest {
        val prevUnsuffledListOfSports = viewModel.latestSport
        viewModel.sharedFlowInit()
        advanceUntilIdle()
        assertEquals(prevUnsuffledListOfSports.size, 1)
    }

    @Test
    fun `check list gets populated with 9 objects after sharedFlowInit() called`() = runTest {
        viewModel.sharedFlowInit()
        advanceUntilIdle()
        val postShuffledListOfSports = viewModel.latestSport
        assertEquals(postShuffledListOfSports.size, 9)
    }

}
