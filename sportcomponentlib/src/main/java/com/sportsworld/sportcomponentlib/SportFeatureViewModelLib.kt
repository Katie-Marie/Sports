package com.sportsworld.sportcomponentlib

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SportFeatureViewModelLib() : ViewModel() {
    val sportRepository by lazy { ContentRepository() }
    var latestSport = listOf(Sport("", ""))

    private val _sharedFlow = MutableStateFlow<List<Sport>>(latestSport)
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        sharedFlowInit()
    }

    fun sharedFlowInit() {
        viewModelScope.launch {
            for(i in 1..1000){
                delay(1000)
                // making the list random, so when I take first element of list it'll be a random Sport.
                latestSport = sportRepository.getFeaturedSports().shuffled()
                _sharedFlow.emit(latestSport)
            }
        }
    }

}
