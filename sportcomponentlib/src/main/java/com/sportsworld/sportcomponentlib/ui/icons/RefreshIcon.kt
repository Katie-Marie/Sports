package com.sportsworld.sportcomponentlib.ui.icons

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable

@Composable
fun RefreshIcon(onClick: () -> Unit, function: () -> Unit) {
    IconButton(
        onClick = onClick,
        content = {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "refresh",
            )
        },
    )
}

