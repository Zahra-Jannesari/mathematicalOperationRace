package com.zarisa.numbergame

object State{
    var numberA=""
    var numberB=""
    var button1=""
    var button2=""
    var button3=""
    var button4=""
    var GroupVisibility=false
    var isAnswer=false
    var level=0
    var score=0
    var randomIndex=0
    val buttonList= mutableListOf<String>()
    init {
        buttonList.add(button1)
        buttonList.add(button2)
        buttonList.add(button3)
        buttonList.add(button4)
    }
}