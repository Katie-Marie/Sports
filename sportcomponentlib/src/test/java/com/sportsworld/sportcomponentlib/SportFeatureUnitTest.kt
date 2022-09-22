package com.sportsworld.sportcomponentlib

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule


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
        viewModel.loadNewSports()
        val firstShuffle = viewModel.uiState.value
        advanceUntilIdle()
        viewModel.loadNewSports()
        val secondShuffle = viewModel.uiState
        advanceUntilIdle()
        assertNotEquals(firstShuffle, secondShuffle)
    }

    @Test
    fun `check list gets populated with 9 objects after sharedFlowInit() called`() = runTest {
        viewModel.loadNewSports()
        advanceUntilIdle()
        val postShuffledListOfSports = viewModel.uiState.value
        assertEquals(postShuffledListOfSports.size, 9)
    }

}
