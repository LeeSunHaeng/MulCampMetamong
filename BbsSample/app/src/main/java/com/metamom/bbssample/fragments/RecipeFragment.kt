package com.metamom.bbssample.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.metamom.bbssample.R
import com.metamom.bbssample.databinding.FragmentRecipeBinding
import com.metamom.bbssample.recipe2.DetailRecipe
import com.metamom.bbssample.recipe2.ImageRoader
import com.metamom.bbssample.recipe2.RecipeDao
import com.metamom.bbssample.recipe2.RecipeDto

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