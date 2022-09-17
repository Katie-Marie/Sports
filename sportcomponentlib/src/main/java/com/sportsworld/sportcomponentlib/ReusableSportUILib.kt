package com.sportsworld.sportcomponentlib


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.sportsworld.sportcomponentlib.ui.theme.SportsComponentLib
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import androidx.lifecycle.viewmodel.compose.viewModel


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
    MainScreen(viewModel.sharedFlow)
}

@Composable
//Todo: read up on side effects in JetPack Compose with coroutines - would not put this code into production until I understood these ideas better
fun MainScreen(sharedFlow: SharedFlow<List<Sport>>) {

    val messages = remember { mutableStateListOf<List<Sport>>() }

    LaunchedEffect(key1 = Unit) {
        sharedFlow.collect {
            messages.add(0, it)
            // Todo: Learn more about recomposition so I can find a better way to achieve this
            messages.removeRange(1,messages.size)
        }
    }
    LazyColumn {
        items(messages) {
            val sport = it[0].name
            val description = it[0].description
            Column {
                Text(
                    text = sport,
                    style = MaterialTheme.typography.h3,
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.h5,
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SportsComponentLib() {
        val viewModel: SportFeatureViewModelLib = viewModel()
        MainScreen(viewModel.sharedFlow)
    }
}