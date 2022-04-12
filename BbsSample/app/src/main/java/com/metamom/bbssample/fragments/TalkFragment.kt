package com.metamom.bbssample.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.metamom.bbssample.R
import com.metamom.bbssample.databinding.FragmentTalkBinding
import com.metamom.bbssample.sns.FragSnsCustomAdapter
import com.metamom.bbssample.sns.SnsDao

class TalkFragment : Fragment() {



    private lateinit var binding : FragmentTalkBinding

    var data = SnsDao.getInstance().allSns()
    lateinit var adaptered:FragSnsCustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*val i = Intent(getActivity(),SnsActivity::class.java)
        startActivity(i)*/
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.snsFragmentRecyclerView)

        adaptered = FragSnsCustomAdapter(this.context!!,data!!,this.parentFragmentManager)
        recycler.adapter = adaptered
        val layout = LinearLayoutManager(this.context)
        recycler.layoutManager = layout
        recycler.setHasFixedSize(true)
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //val recycler = container!!.findViewById<RecyclerView>(R.id.snsFragmentRecyclerView)


        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_talk, container, false)

        val recycler = binding.snsFragmentRecyclerView

        adaptered = FragSnsCustomAdapter(this.context!!,data!!,this.parentFragmentManager)
        recycler.adapter = adaptered
        val layout = LinearLayoutManager(this.context)
        recycler.layoutManager = layout
        recycler.setHasFixedSize(true)

        /*binding.snsFragmentRecyclerView.adapter = adaptered
        val layout = LinearLayoutManager(contxt)
        binding.snsFragmentRecyclerView.layoutManager = layout
        binding.snsFragmentRecyclerView.setHasFixedSize(true)*/

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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                400->{
                    val position = data?.getSerializableExtra("position") as Int
                    adaptered.update(position)
                    // TalkFragment.adap.update(position)
                }

                200->{
                    val position = data?.getSerializableExtra("position") as Int
                    adaptered.snsList.get(position).content = data.getStringExtra("content") as String
                    adaptered.snsList.get(position).imagecontent = data.getStringExtra("uri") as String
                    adaptered.notifyItemChanged(position)
                }

            }
        }
    }

    fun startComment(i:Intent, code:Int){
        activity?.startActivityForResult(i,code)
        //startActivityForResult(i,code)
    }

}