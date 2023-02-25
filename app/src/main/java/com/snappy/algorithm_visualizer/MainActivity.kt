package com.snappy.algorithm_visualizer

import android.media.MediaDrm.OnEventListener
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.snappy.algorithm_visualizer.algorithms.InsertionSort
import com.snappy.algorithm_visualizer.components.VisualizerBottomBar
import com.snappy.algorithm_visualizer.components.VisualizerSection
import com.snappy.algorithm_visualizer.ui.theme.AlgorithmVisualizerTheme

class MainActivity : ComponentActivity() {

    private  val viewModel: AlgorithmViewModel by lazy {
        val viewModelFactory = AlgorithmViewModelProvider(InsertionSort())
        ViewModelProvider(this, viewModelFactory)[AlgorithmViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlgorithmVisualizerTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.BottomCenter
                ){
                    Column {
                        VisualizerSection(
                            arr = viewModel.arr.value,
                            modifier =Modifier.fillMaxWidth()
                        )

                        val isPlaying = viewModel.isPlaying.value
                        val isFinished = viewModel.onSortingFinish.value

                        VisualizerBottomBar(
                            modifier = Modifier.fillMaxWidth().height(75.dp),
                            isPlaying = if(isFinished) !isFinished else isPlaying,
                            playpauseClick = { viewModel.events(AlgorithmEvents.PlayPause) },
                            slowDownClick = { viewModel.events(AlgorithmEvents.SlowDown) },
                            speedUp = { viewModel.events(AlgorithmEvents.SpeedUp) },
                            previousClick = { viewModel.events(AlgorithmEvents.Previous) },
                            nextClick = { viewModel.events(AlgorithmEvents.Next) })
                    }
                }
            }
        }
    }
}
