package com.sportsworld.learnsharedflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sportsworld.learnsharedflow.ui.theme.LearnSharedFlowTheme
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import com.sportsworld.sportcomponentlib.DisplayScreenSetup



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

                    ShellAppScaffold()
                    }

                }
            }
        }
    }


@Composable
fun ShellAppScaffold() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(title = {Text("Featured Sport")},backgroundColor = MaterialTheme.colors.primary)  },
        content = { DisplayScreenSetup()},
    )
}