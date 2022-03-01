package com.zarisa.numbergame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zarisa.numbergame.databinding.ActivityMainBinding
import com.zarisa.numbergame.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    lateinit var binding: ActivityScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getScore()
        binding.buttonPlayAgain.setOnClickListener { playAgain() }
        binding.exit.setOnClickListener { finish() }
    }
    fun getScore(){
        var score=intent.getIntExtra("score",0)
        binding.textViewShowUserScore.text= "Your score is $score"
    }
    fun playAgain(){
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}