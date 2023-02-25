package com.snappy.algorithm_visualizer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.snappy.algorithm_visualizer.algorithms.InsertionSort

class AlgorithmViewModelProvider(
    private var insertionSort: InsertionSort
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlgorithmViewModel(insertionSort) as T
    }
}