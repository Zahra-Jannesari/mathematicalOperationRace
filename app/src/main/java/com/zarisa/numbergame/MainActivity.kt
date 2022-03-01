package com.zarisa.numbergame

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.zarisa.numbergame.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var listButtons = mutableListOf<Button>()
    var score = 0
    var level = 0
    var randomIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        level=0
    }

    private fun initView() {

        binding.buttonDice.setOnClickListener { dice() }
        listButtons.add(binding.button1)
        listButtons.add(binding.button2)
        listButtons.add(binding.button3)
        listButtons.add(binding.button4)
        listButtons.forEach {
            it.setOnClickListener {
                checkAnswer(listButtons.indexOf(it))
            }
        }
        binding.textViewScore.text = score.toString()

    }

    private fun dice() {
        if (level==4){
            val intent=Intent(this,ScoreActivity::class.java)
            intent.putExtra("score",score)
            startActivity(intent)
        }
        level++
        listButtons.forEach { it.isClickable=true}
        clearColor()
        listButtons.forEach { it.text = "" }
        var randomA = Random().nextInt(99) + 1
        binding.textViewNumberA.text = randomA.toString()
        var randomB = Random().nextInt(9) + 1
        binding.textViewNumberB.text = randomB.toString()
        randomIndex = Random().nextInt(listButtons.size - 1)
        var div = divide(randomA, randomB)
        listButtons[randomIndex].text = div.toString()
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
        }
    }

    fun checkAnswer(butIndex: Int) {
        if (butIndex == randomIndex) {
            score += 5
            listButtons[butIndex].setBackgroundColor(Color.GREEN)
        } else {
            score -= 2
            listButtons[butIndex].setBackgroundColor(Color.RED)
        }
        binding.textViewScore.text=score.toString()
        listButtons.forEach { it.isClickable=false }
    }
    fun divide(randomA: Int, randomB: Int): Int {
        return randomA % randomB
    }
    fun clearColor(){
        listButtons.forEach {
            it.setBackgroundColor(Color.BLUE)
        }
    }

}