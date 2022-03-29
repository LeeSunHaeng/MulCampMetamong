package com.metamom.bbssample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.metamom.bbssample.R
import com.metamom.bbssample.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private lateinit var binding : FragmentRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe, container, false)

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_recipeFragment_to_homeFragment)
        }

        binding.mealTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_recipeFragment_to_mealFragment)
        }

        binding.talkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_recipeFragment_to_talkFragment)
        }

        binding.accountTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_recipeFragment_to_accountFragment)
        }

        return binding.root
    }
}