package com.snappy.algorithm_visualizer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.snappy.algorithm_visualizer.R

@Composable
fun VisualizerBottomBar(
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
    playpauseClick: () -> Unit,
    slowDownClick: () -> Unit,
    speedUp: ()-> Unit,
    previousClick: () -> Unit,
    nextClick: () -> Unit,
    isPlaying: Boolean = false
){
    BottomAppBar(
        modifier = modifier,
    ) {
        Row (
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            IconButton(onClick = slowDownClick) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_remove_24),
                    contentDescription = "Slow Down",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(onClick = playpauseClick) {
                Icon(
                    painter = painterResource(
                        id = if(!isPlaying) R.drawable.baseline_play_circle_outline_24
                        else R.drawable.baseline_pause_circle_outline_24
                    ),
                    contentDescription = "Play/Pause",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(onClick = speedUp) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "Speed Up",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(onClick = previousClick) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_navigate_before_24),
                    contentDescription = "Previous Click",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(onClick = nextClick) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_navigate_next_24),
                    contentDescription = "Next Click",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

        }
    }
}

