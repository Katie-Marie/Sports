package com.sportsworld.sportcomponentlib


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.sportsworld.sportcomponentlib.ui.theme.SportsComponentLib
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sportsworld.sportcomponentlib.ui.icons.RefreshIcon
import kotlinx.coroutines.launch


@Composable
fun TopBarLib() {
    TopAppBar(title = {
        Text(
            text = "Featured Sport",
            style = MaterialTheme.typography.h6,
        )
    })
}

@Composable
fun DisplayScreenSetup(viewModel: SportFeatureViewModelLib = viewModel()) {
    MainScreen(viewModel)
}

@Composable
fun RefreshButtonLib(viewModel: SportFeatureViewModelLib = viewModel()) {
    val scope = rememberCoroutineScope()
    // In practice would pull the last sport displayed from Shared Prefs.
    var currentSport = Sport.createMockedSports()[0]

    val getNewSportOnClick: () -> Unit = {
        scope.launch {
            try {

                // try and collect the value from the flow and then print it out
                viewModel.loadNews()

                val listOfSports = viewModel.uiState.value

                Log.d("katie", currentSport.name) // testing current sport is getting updated
                // Don't want to display same sport twice in a row
                if(currentSport == listOfSports[0])
                    currentSport = listOfSports[1]
                else
                    currentSport = listOfSports[0]

            } catch (ex: Exception) {
                println("Coroutine to get current list of sports cancelled")
            }
        }
    }

    RefreshIcon(onClick = getNewSportOnClick) {
  }
}

@Composable
fun MainScreen(viewModel: SportFeatureViewModelLib = viewModel()) {
    AtNewUIstate(viewModel)
}

@Composable
fun AtNewUIstate(viewModel: SportFeatureViewModelLib = viewModel()){
        val state = viewModel.uiState.collectAsState()
        val stateValue = state.value

        if(stateValue.isEmpty()){
           Text("New Sport Feature is Loading...")
        }
        else{
            Column {
                Text(
                    text = stateValue[0].name,
                    style = MaterialTheme.typography.h3,
                )
                Text(
                    text = stateValue[0].description,
                    style = MaterialTheme.typography.h5,
                )
            }
        }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SportsComponentLib() {
        val viewModel: SportFeatureViewModelLib = viewModel()
        MainScreen(viewModel)
    }
}