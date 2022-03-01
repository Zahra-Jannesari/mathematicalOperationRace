package com.zarisa.numbergame

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.zarisa.numbergame.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var listButtons = mutableListOf<Button>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    private fun initView() {
        listButtons.add(binding.button1)
        listButtons.add(binding.button2)
        listButtons.add(binding.button3)
        listButtons.add(binding.button4)
        binding.textViewScore.text ="Score:"+ State.score.toString()
        if(State.GroupVisibility)
            binding.groupAnswers.visibility=View.VISIBLE
        else
            binding.groupAnswers.visibility=View.GONE
        binding.button1.text=State.button1
        binding.button2.text=State.button2
        binding.button3.text=State.button3
        binding.button4.text=State.button4
        binding.textViewNumberA.text=State.numberA
        binding.textViewNumberB.text=State.numberB
        if (State.isAnswer)
            listButtons.forEach { it.isClickable=false}
        binding.buttonDice.setOnClickListener {dice()}
        listButtons.forEach {
            it.setOnClickListener {
                checkAnswer(listButtons.indexOf(it))
            }
        }
    }

    private fun dice() {
        if (State.level==4){
            val intent=Intent(this,ScoreActivity::class.java)
            intent.putExtra("score",State.score)
            startActivity(intent)
        }
        State.level++
        listButtons.forEach { it.isClickable=true}
        clearColor()
        listButtons.forEach { it.text = "" }
        var randomA = Random().nextInt(99) + 1
        State.numberA=randomA.toString()
        binding.textViewNumberA.text =State.numberA
        var randomB = Random().nextInt(9) + 1
        State.numberB=randomB.toString()
        binding.textViewNumberB.text = State.numberB
        State.randomIndex=Random().nextInt(listButtons.size - 1)
        var div = divide(randomA, randomB)
        listButtons[State.randomIndex].text = div.toString()
        var listTextCheck = mutableListOf<String>()
        listTextCheck.add(div.toString())
        listButtons.forEach {
            if (it.text == "") {
                while (it.text=="") {
                    var randAnswer =(Random().nextInt(9)+1).toString()
                    if (!listTextCheck.contains(randAnswer)) {
                        listTextCheck.add(randAnswer)
                        it.text = (randAnswer)
                    }
                }
            }
            binding.groupAnswers.visibility = View.VISIBLE
            State.GroupVisibility=true
        }
    }

    fun checkAnswer(butIndex: Int) {
        if (butIndex == State.randomIndex) {
            State.score += 5
            listButtons[butIndex].setBackgroundColor(Color.GREEN)
        } else {
            State.score -= 2
            listButtons[butIndex].setBackgroundColor(Color.RED)
        }
        binding.textViewScore.text="Score:"+State.score.toString()
        listButtons.forEach { it.isClickable=false }
    }
    private fun divide(randomA: Int, randomB: Int): Int {
        return randomA % randomB
    }
    private fun clearColor() {
        listButtons.forEach {
            it.setBackgroundColor(Color.BLUE)
        }
    }

}