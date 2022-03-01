package com.zarisa.numbergame

import android.content.Intent
import android.os.Bundle
import android.os.Process
import androidx.appcompat.app.AppCompatActivity
import com.zarisa.numbergame.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    lateinit var binding: ActivityScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getScore()
        binding.buttonPlayAgain.setOnClickListener { playAgain() }
        binding.exit.setOnClickListener {
            this.finishAffinity()
        }
    }
    fun getScore(){
        var score=intent.getIntExtra("score",0)
        binding.textViewShowUserScore.text= "Your score is $score"
    }
    fun playAgain(){
        State.level=0
        State.score=0
        State.GroupVisibility=false
        State.numberA=""
        State.numberB=""
        State.button1=""
        State.button2=""
        State.button3=""
        State.button4=""
        State.isAnswer=false
        State.randomIndex=0
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}