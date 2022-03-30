package com.zarisa.numbergame.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.zarisa.numbergame.R
import com.zarisa.numbergame.databinding.FragmentScoreBinding
import com.zarisa.numbergame.model.RaceViewModel

class ScoreFragment : Fragment() {
    lateinit var binding:FragmentScoreBinding
    val viewModel: RaceViewModel by activityViewModels()
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
        if (score> viewModel.record)
            viewModel.record =score
        binding.textViewRecord.text="Your record is ${viewModel.record}"
    }
    private fun playAgain(){
        saveState()
        findNavController().navigate(R.id.action_scoreFragment_to_startGameFragment)
    }
    fun saveState(){
        viewModel.level =0
        viewModel.score =0
        viewModel.GroupVisibility =false
        viewModel.numberA =""
        viewModel.numberB =""
        viewModel.choiceButtonTrue =false
        viewModel.choiceButtonFalse =false
        viewModel.buttonList = mutableListOf("","","","")
        viewModel.isAnswer =false
        viewModel.randomIndex =0
        viewModel.buttonIsWrong =0
    }
}