package com.zarisa.numbergame.model

import android.icu.text.UCharacterIterator.DONE
import android.os.Build
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RaceViewModel:ViewModel(){
    var numberA=""
    var numberB=""
    var GroupVisibility=false
    var isAnswer=false
    var level=0
    var score=0
    var randomIndex=0
    var buttonList=mutableListOf("","","","")
    var choiceButtonTrue=false
    var choiceButtonFalse=false
    var buttonIsWrong=0
    var record=0


    //timer part
    companion object {
        private const val DONE = 0L
        // This is the number of milliseconds in a second
        private const val ONE_SECOND = 1000L

        // This is the time of each level
        private const val COUNTDOWN_TIME = 60000L

    }
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime
    private lateinit var timer: CountDownTimer
    init {
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun onFinish() {
                _currentTime.value = DONE
            }
        }
    }

    fun timerControl(start:Boolean) {
        if(start)timer.start() else timer.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}