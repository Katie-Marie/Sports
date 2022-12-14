package com.sportsworld.sportcomponentlib

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SportFeatureViewModelLib : ViewModel() {
    val sportRepository by lazy { ContentRepository() }

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow<List<Sport>>(emptyList())

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<List<Sport>> = _uiState

    init {
        // allow us to populate the composable initially
        loadNewSports()
    }

    fun loadNewSports() {
        viewModelScope.launch {
            val newSportList = sportRepository.getFeaturedSports().shuffled()
            _uiState.value = newSportList
        }
    }

}
