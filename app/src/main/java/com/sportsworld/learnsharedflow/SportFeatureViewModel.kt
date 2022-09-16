package com.sportsworld.learnsharedflow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SportFeatureViewModel : ViewModel() {

    private val _sharedFlow = MutableStateFlow<Int>(0)
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        sharedFlowInit()
    }

    fun sharedFlowInit() {
        viewModelScope.launch {
            for (i in 1..1000) {
                delay(2000)
                _sharedFlow.emit(i)
            }
        }
    }

}
