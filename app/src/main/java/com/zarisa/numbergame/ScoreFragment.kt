package com.zarisa.numbergame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.zarisa.numbergame.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {
    lateinit var binding:FragmentScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentScoreBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Score"
        initView()
    }
    fun initView(){
        getScoreAndRecord()
        binding.buttonPlayAgain.setOnClickListener { playAgain() }
        binding.exit.setOnClickListener {
            saveState()
            finishAffinity(requireActivity())
        }
    }
    @SuppressLint("SetTextI18n")
    fun getScoreAndRecord(){
        //TODO
        //get score from view model and put in var score
        var score= 0
        binding.textViewShowUserScore.text= "Your score is $score"
        if (score>State.record)
            State.record=score
        binding.textViewRecord.text="Your record is ${State.record}"
    }
    private fun playAgain(){
        saveState()
        findNavController().navigate(R.id.action_scoreFragment_to_startGameFragment)
    }
    fun saveState(){
        State.level=0
        State.score=0
        State.GroupVisibility=false
        State.numberA=""
        State.numberB=""
        State.choiceButtonTrue=false
        State.choiceButtonFalse=false
        State.buttonList= mutableListOf("","","","")
        State.isAnswer=false
        State.randomIndex=0
        State.buttonIsWrong=0
    }
}