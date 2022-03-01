package com.zarisa.numbergame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.zarisa.numbergame.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var listButtons= mutableListOf<Button>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

    }
    private fun initView(){
        binding.buttonDice.setOnClickListener { dice() }
        listButtons.add(binding.button1)
        listButtons.add(binding.button2)
        listButtons.add(binding.button3)
        listButtons.add(binding.button4)
    }
    private fun dice(){
        listButtons.forEach { it.text="" }
        var randomA=Random().nextInt(99)+1
        binding.textViewNumberA.text=randomA.toString()
        var randomB=Random().nextInt(9)+1
        binding.textViewNumberB.text=randomB.toString()
        var randomIndex=Random().nextInt(listButtons.size-1)
        var div=div(randomA,randomB)
        listButtons[randomIndex].text=div.toString()
        listButtons.forEach {
            if(it.text==""){
                it.text=(div-Random().nextInt(div)+1).toString()
            }
        }
        binding.groupAnswers.visibility= View.VISIBLE
    }
    fun div(a: Int, b:Int):Int{
        return a%b
    }
}