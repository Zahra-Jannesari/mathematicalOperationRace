package com.zarisa.numbergame

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.zarisa.numbergame.databinding.ActivityMainBinding
import com.zarisa.numbergame.databinding.FragmentGameBinding
import java.util.*

@SuppressLint("SetTextI18n")
class GameFragment : Fragment() {
   lateinit var binding:FragmentGameBinding

    private var listButtons = mutableListOf<Button>()
    //    lateinit var countDownTimer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentGameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Game"
        initView()
    }
    private fun initView() {
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
            findNavController().navigate(R.id.action_gameFragment_to_scoreFragment)
        }
        else {
//            countDownTimer.cancel()
//            timerFun()
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

//    private fun timerFun() {
//        countDownTimer=object : CountDownTimer(10000, 1000) {
//
//            override fun onTick(millisUntilFinished: Long) {
//                binding.textViewTimer.setText( "time: " + millisUntilFinished / 1000)
//            }
//            override fun onFinish() {
//                Toast.makeText(this@MainActivity,"Time done!",Toast.LENGTH_SHORT).show()
//                dice()
//            }
//        }.start()
//    }

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