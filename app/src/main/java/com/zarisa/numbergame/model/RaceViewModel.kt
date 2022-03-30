package com.zarisa.numbergame.model

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
}