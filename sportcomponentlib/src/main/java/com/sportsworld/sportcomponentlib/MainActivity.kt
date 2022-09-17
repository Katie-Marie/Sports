package com.sportsworld.sportcomponentlib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.sportsworld.sportcomponentlib.ui.theme.SportsComponentLib


class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<SportFeatureViewModelLib>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportsComponentLib() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        //This will be in other apps
                        topBar = {topBar()},
                        // This will be supplied by the SportsFeature library - once I refactor to create one..
                        content = {DisplayScreenSetup()}
                    )
                }
            }
        }
    }
}

