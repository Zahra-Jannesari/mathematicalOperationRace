package com.zarisa.numbergame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zarisa.numbergame.databinding.ActivityMainBinding

class ScoreActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getScore()
    }
    fun getScore(){
        var score=intent.getIntExtra("score",0)
        binding.textViewScore.text= "Your scor is $score"
    }
    fun playAgain(){

    }
}