package com.metamom.bbssample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.metamom.bbssample.R
import com.metamom.bbssample.databinding.FragmentMealBinding

class MealFragment : Fragment() {

    private lateinit var binding : FragmentMealBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_meal, container, false)

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_mealFragment_to_homeFragment)
        }

        binding.recipeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_mealFragment_to_recipeFragment)
        }

        binding.talkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_mealFragment_to_talkFragment)
        }

        binding.accountTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_mealFragment_to_accountFragment)
        }

        return binding.root
    }

}