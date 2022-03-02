package com.zarisa.numbergame

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.zarisa.numbergame.databinding.ActivityMainBinding
import java.util.*
@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var listButtons = mutableListOf<Button>()
    lateinit var countDownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    private fun initView() {
        binding.textViewTimer.text = State.timer
        listButtons.add(binding.button1)
        listButtons.add(binding.button2)
        listButtons.add(binding.button3)
        listButtons.add(binding.button4)
        binding.buttonDice.setOnClickListener {dice()}
        listButtons.forEach {
            it.setOnClickListener {
                checkAnswer(listButtons.indexOf(it))
            }
        }
        configChangeHandling()
    }

    private fun configChangeHandling() {
        if (State.choiceButtonTrue) {
            listButtons[State.randomIndex].setBackgroundColor(Color.GREEN)
            listButtons.forEach { it.isClickable = false }
        }
        if (State.choiceButtonFalse) {
            listButtons[State.buttonIsWrong].setBackgroundColor(Color.RED)
            listButtons.forEach { it.isClickable = false }
        }
        binding.textViewScore.text ="Score: ${State.score}"
        binding.textViewRecord.text="Record: ${State.record}"
        if(State.GroupVisibility)
            binding.groupAnswers.visibility=View.VISIBLE
        else
            binding.groupAnswers.visibility=View.GONE
        listButtons.forEach { it.text=State.buttonList[listButtons.indexOf(it)] }
        binding.textViewNumberA.text=State.numberA
        binding.textViewNumberB.text=State.numberB
    }

    private fun dice() {
        if (State.level >= 5) {
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("score", State.score)
            startActivity(intent)
        }
        else {
//            countDownTimer.cancel()
            timerFun()
            State.level++
            listButtons.forEach { it.isClickable = true }
            clearColor()
            listButtons.forEach { it.text = "" }
            var randomA = Random().nextInt(99) + 1
            State.numberA = randomA.toString()
            binding.textViewNumberA.text = State.numberA
            var randomB = Random().nextInt(9) + 1
            State.numberB = randomB.toString()
            binding.textViewNumberB.text = State.numberB
            State.randomIndex = Random().nextInt(listButtons.size - 1)
            var div = divide(randomA, randomB)
            listButtons[State.randomIndex].text = div.toString()
            var listTextCheck = mutableListOf<String>()
            listButtons.forEach {
                if (listButtons.indexOf(it) == State.randomIndex)
                    listTextCheck.add(div.toString())
                else {
                    if (it.text == "") {
                        while (it.text == "") {
                            var randAnswer = (Random().nextInt(9) + 1)
                            if (!listTextCheck.contains(randAnswer.toString())&&randAnswer!=div) {
                                listTextCheck.add(randAnswer.toString())
                                it.text = (randAnswer.toString())
                            }
                        }
                    }
                }
                binding.groupAnswers.visibility = View.VISIBLE
                State.GroupVisibility = true
                State.buttonList = listTextCheck
            }
            State.choiceButtonTrue = false
            State.choiceButtonFalse = false
            State.buttonIsWrong = 0
        }
    }

    private fun timerFun() {
        countDownTimer=object : CountDownTimer(10000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                State.timer="time: " + millisUntilFinished / 1000
                binding.textViewTimer.text = State.timer
            }
            override fun onFinish() {
                Toast.makeText(this@MainActivity,"Time done!",Toast.LENGTH_SHORT).show()
                dice()
            }
        }.start()
    }

    fun checkAnswer(butIndex: Int) {
        if (butIndex == State.randomIndex) {
            State.score += 5
            listButtons[butIndex].setBackgroundColor(Color.GREEN)
            State.choiceButtonTrue=true
        } else {
            State.score -= 2
            listButtons[butIndex].setBackgroundColor(Color.RED)
            State.choiceButtonFalse=true
            State.buttonIsWrong=butIndex
        }
        binding.textViewScore.text="Score: ${State.score}"
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