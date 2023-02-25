package com.snappy.algorithm_visualizer.algorithms

class InsertionSort {
    suspend fun sort(
        arr: IntArray,
        onSwap: (IntArray) -> Unit
    ){
        for( i in 1 until arr.size){
            var j = i-1;
            var key = arr[i]
            while (j >=0 && key<arr[j]){
                arr[j+1] = arr[i]
                onSwap(arr)
                j--
            }
            arr[j+1] = key
            onSwap(arr)
        }
    }
}