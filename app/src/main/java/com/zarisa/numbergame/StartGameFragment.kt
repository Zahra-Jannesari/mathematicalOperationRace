package com.zarisa.numbergame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.zarisa.numbergame.databinding.FragmentStartGameBinding

//for now its just for start, later we add optional range and operation
class StartGameFragment : Fragment() {
    lateinit var binding: FragmentStartGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartGameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO
        //Chose a good name for this fragment
        (activity as AppCompatActivity).supportActionBar?.title = "Start"
        initViews()
        foo()
    }

    private fun foo() {
        TODO("")
    }

    private fun initViews() {
        binding.buttonStart.setOnClickListener { findNavController().navigate(R.id.action_startGameFragment_to_gameFragment) }
    }
}