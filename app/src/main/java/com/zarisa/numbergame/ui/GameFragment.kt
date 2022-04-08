package com.zarisa.numbergame.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Observer
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.zarisa.numbergame.R
import com.zarisa.numbergame.databinding.FragmentGameBinding
import com.zarisa.numbergame.model.RaceViewModel
import java.util.*

@SuppressLint("SetTextI18n")
class GameFragment : Fragment() {
   lateinit var binding:FragmentGameBinding
    val viewModel: RaceViewModel by activityViewModels()
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
        viewModel.currentTime.observe(viewLifecycleOwner, Observer { newTime ->
            binding.timerText.text = DateUtils.formatElapsedTime(newTime)
        })
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
        if (viewModel.choiceButtonTrue) {
            listButtons[viewModel.randomIndex].setBackgroundColor(Color.GREEN)
            listButtons.forEach { it.isClickable = false }
        }
        if (viewModel.choiceButtonFalse) {
            listButtons[viewModel.buttonIsWrong].setBackgroundColor(Color.RED)
            listButtons.forEach { it.isClickable = false }
        }
        binding.textViewScore.text ="Score: ${viewModel.score}"
        binding.textViewRecord.text="Record: ${viewModel.record}"
        if(viewModel.GroupVisibility)
            binding.groupAnswers.visibility=View.VISIBLE
        else
            binding.groupAnswers.visibility=View.GONE
        listButtons.forEach { it.text= viewModel.buttonList[listButtons.indexOf(it)] }
        binding.textViewNumberA.text= viewModel.numberA
        binding.textViewNumberB.text= viewModel.numberB
    }

    private fun dice() {
        viewModel.timerControl(true)
        if (viewModel.level >= 5) {
            findNavController().navigate(R.id.action_gameFragment_to_scoreFragment)
        }
        else {
//            countDownTimer.cancel()
//            timerFun()
            viewModel.level++
            listButtons.forEach { it.isClickable = true }
            clearColor()
            listButtons.forEach { it.text = "" }
            var randomA = Random().nextInt(99) + 1
            viewModel.numberA = randomA.toString()
            binding.textViewNumberA.text = viewModel.numberA
            var randomB = Random().nextInt(9) + 1
            viewModel.numberB = randomB.toString()
            binding.textViewNumberB.text = viewModel.numberB
            viewModel.randomIndex = Random().nextInt(listButtons.size - 1)
            var div = divide(randomA, randomB)
            listButtons[viewModel.randomIndex].text = div.toString()
            var listTextCheck = mutableListOf<String>()
            listButtons.forEach {
                if (listButtons.indexOf(it) == viewModel.randomIndex)
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
                viewModel.GroupVisibility = true
                viewModel.buttonList = listTextCheck
            }
            viewModel.choiceButtonTrue = false
            viewModel.choiceButtonFalse = false
            viewModel.buttonIsWrong = 0
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
//override fun onTick(millisUntilFinished: Long) {
//    binding.timer.setText("Timer: " + millisUntilFinished / 1000)
//}

    fun checkAnswer(butIndex: Int) {
        viewModel.timerControl(true)
        if (butIndex == viewModel.randomIndex) {
            viewModel.score += 5
            listButtons[butIndex].setBackgroundColor(Color.GREEN)
            viewModel.choiceButtonTrue =true
        } else {
            viewModel.score -= 2
            listButtons[butIndex].setBackgroundColor(Color.RED)
            viewModel.choiceButtonFalse =true
            viewModel.buttonIsWrong =butIndex
        }
        binding.textViewScore.text="Score: ${viewModel.score}"
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
