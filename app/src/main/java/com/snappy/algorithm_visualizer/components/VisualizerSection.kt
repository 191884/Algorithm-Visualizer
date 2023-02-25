package com.snappy.algorithm_visualizer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier

@Composable
fun VisualizerSection(
    modifier: androidx.compose.ui.Modifier= androidx.compose.ui.Modifier,
    arr: IntArray
){
    BoxWithConstraints(modifier = modifier) {
        val maxHeight = maxWidth - 75.dp
        val itemWidth = remember {
            maxHeight /arr.size - 8.dp
        }
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            arr.forEach {
                Box(modifier = androidx.compose.ui.Modifier
                    .height(if(it.dp > maxHeight) maxHeight else it.dp)
                    .width(itemWidth)
                    .background(MaterialTheme.colorScheme.secondary)
                )
            }
        }
    }
}