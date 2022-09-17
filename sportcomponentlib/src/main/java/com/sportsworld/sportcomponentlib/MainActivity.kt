package com.sportsworld.sportcomponentlib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import com.sportsworld.sportcomponentlib.ui.theme.SportsComponentLib


class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<SportFeatureViewModelLib>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportsComponentLib() {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = { TopBarLib() },
                        content = {DisplayScreenSetup()}
                    )
                }
            }
        }
    }
}

