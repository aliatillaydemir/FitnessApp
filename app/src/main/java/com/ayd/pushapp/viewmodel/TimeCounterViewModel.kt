package com.ayd.pushapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimeCounterViewModel: ViewModel() {

    private var countDownJob: Job? = null

    fun startCountDown(seconds: Int, onTick: (Int) -> Unit, onFinish: () -> Unit) {
        countDownJob?.cancel() // Cancel the previous countdown job if any
        countDownJob = viewModelScope.launch(Dispatchers.Main) {
            var remainingSeconds = seconds
            while (remainingSeconds > 0) {
                onTick(remainingSeconds)
                delay(1000)
                remainingSeconds--
            }
            onFinish()
        }
    }

    fun resetCountdown() {
        countDownJob?.cancel()
        // Additional logic for reset, if needed
    }

    override fun onCleared() {
        countDownJob?.cancel() // Cancel the countdown job when the ViewModel is cleared
        super.onCleared()
    }

}