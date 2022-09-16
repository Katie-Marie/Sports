package com.sportsworld.learnsharedflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sportsworld.learnsharedflow.ui.theme.LearnSharedFlowTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.SharedFlow
import androidx.compose.ui.platform.LocalLifecycleOwner
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnSharedFlowTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenSetup()


                }
            }
        }
    }
}
@Composable
fun ScreenSetup(viewModel: SportFeatureViewModel = viewModel()) {
    MainScreen(viewModel.sharedFlow)
}

@Composable
//Todo: read up on coroutines and side effects in JetPack Compose - would not put this code into production until I understoof these ideas better
fun MainScreen(sharedFlow: SharedFlow<Int>) {

    val messages = remember { mutableStateListOf<Int>()}
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = Unit) {
        sharedFlow.collectLatest {
            messages.add(0, it)
            // Todo: Learn more about best practices so I can find a better way
            messages.removeRange(1,messages.size)
        }
    }


    LazyColumn {
        items(messages) {
            Text(
                "Collected Value = $it",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LearnSharedFlowTheme() {
        val viewModel: SportFeatureViewModel = viewModel()
        MainScreen(viewModel.sharedFlow)
    }
}

