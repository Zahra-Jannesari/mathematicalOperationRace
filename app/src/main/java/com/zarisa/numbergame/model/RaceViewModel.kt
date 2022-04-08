package com.zarisa.numbergame.model

import android.view.View
import androidx.lifecycle.ViewModel
import java.util.*

class RaceViewModel:ViewModel(){
    fun dice() {
        level++
        var randomA = Random().nextInt(99) + 1
        numberA = randomA.toString()
        var randomB = Random().nextInt(9) + 1
        numberB = randomB.toString()
        var listTextCheck = mutableListOf<String>()
       choiceButtonTrue = false
       choiceButtonFalse = false
       buttonIsWrong = 0
    }

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
}