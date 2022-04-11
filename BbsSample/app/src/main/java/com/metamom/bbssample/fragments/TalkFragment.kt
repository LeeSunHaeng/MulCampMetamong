package com.metamom.bbssample.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.metamom.bbssample.R
import com.metamom.bbssample.databinding.FragmentTalkBinding
import com.metamom.bbssample.sns.CustomAdapter
import com.metamom.bbssample.sns.SnsActivity
import com.metamom.bbssample.sns.SnsDao

class TalkFragment : Fragment() {

    private lateinit var binding : FragmentTalkBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val i = Intent(getActivity(),SnsActivity::class.java)
        startActivity(i)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_talk, container, false)

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_homeFragment)
        }

        binding.mealTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_mealFragment)
        }

        binding.recipeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_recipeFragment)
        }

        binding.accountTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_accountFragment)
        }
        return binding.root
    }

}