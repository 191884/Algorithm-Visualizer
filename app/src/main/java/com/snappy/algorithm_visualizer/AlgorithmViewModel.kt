package com.snappy.algorithm_visualizer

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snappy.algorithm_visualizer.algorithms.InsertionSort
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AlgorithmViewModel(
    private val insertionSort: InsertionSort
): ViewModel() {



    var arr =
        mutableStateOf(
            intArrayOf(
                50, 43, 165, 400, 244, 126, 54, 98, 54, 65, 2, 4, 509,
                52, 76, 533, 46, 659, 438, 43, 534, 128, 87, 162, 25, 428, 38, 9, 26
            )
        )

    var isPlaying = mutableStateOf(false)
    val onSortingFinish = mutableStateOf(false)
    private var delay = 150L
    private var pause = false
    private var next = 1
    private var previous = 0

    private var sortedArrayLevels = mutableListOf<List<Int>>()

    init {
        viewModelScope.launch {
            insertionSort.sort(
                arr.value!!.clone()
            ) { modifiedArray ->
                sortedArrayLevels.add(modifiedArray.toMutableList())
            }
        }
    }

    fun events(event: AlgorithmEvents){
        when (event){
            is AlgorithmEvents.PlayPause -> {
                playPauseAlgorithm()
            }
            is AlgorithmEvents.SlowDown -> {
                slowDown()
            }
            is AlgorithmEvents.SpeedUp -> {
                speedUp()
            }
            is AlgorithmEvents.Previous -> {
                previous()
            }
            is AlgorithmEvents.Next -> {
                next()
            }
        }
    }

    private fun next() {
        if(next < sortedArrayLevels.size){
            arr.value = sortedArrayLevels[next].toIntArray()
            next++
            previous++
        }
    }

    private fun previous() {
        if( previous >= 0){
            arr.value = sortedArrayLevels[previous].toIntArray()
            next--
            previous--
        }
    }

    private fun slowDown() {
        if(delay >= 150L){
            delay += 15
        }
    }

    private fun speedUp() {
        delay -= 50
    }

    private fun pause() {
        pause = true
    }

    private fun playPauseAlgorithm() {
        if(isPlaying.value!!)
            pause()
        else
            play()
        isPlaying.value = !isPlaying.value!!
    }

    private var sortingState = 0
    private fun play() = viewModelScope.launch {
        pause = false
        for(i in sortingState until sortedArrayLevels.size){
            if(!pause){
                delay(delay)
                arr.value = sortedArrayLevels[i].toIntArray()
            }else{
                sortingState= i
                next = i+1
                previous = i
                return@launch
            }
        }
        onSortingFinish.value = true
    }

}
