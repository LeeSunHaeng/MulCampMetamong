package com.metamom.bbssample.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.metamom.bbssample.MemberUpdateActivity
import com.metamom.bbssample.R
import com.metamom.bbssample.databinding.FragmentAccountBinding
import com.metamom.bbssample.sns.SnsDao
import com.metamom.bbssample.subsingleton.MemberSingleton

class AccountFragment : Fragment() {

    private lateinit var binding : FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_accountFragment_to_homeFragment)
        }

        binding.recipeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_accountFragment_to_recipeFragment)
        }

        binding.talkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_accountFragment_to_talkFragment)
        }

        binding.mealTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_accountFragment_to_mealFragment)
        }

        /* #21# 회원정보 가져와서 출력하기 */
        val idTxt = binding.root.findViewById<TextView>(R.id.myPageFrag_idTxt)
        val nicknameTxt = binding.root.findViewById<TextView>(R.id.myPageFrag_nicknameTxt)
        val nameTxt = binding.root.findViewById<TextView>(R.id.myPageFrag_nameTxt)
        val emailTxt = binding.root.findViewById<TextView>(R.id.myPageFrag_emailTxt)
        val phoneTxt = binding.root.findViewById<TextView>(R.id.myPageFrag_phoneTxt)
        val heightTxt = binding.root.findViewById<TextView>(R.id.myPageFrag_heightTxt)
        val weightTxt = binding.root.findViewById<TextView>(R.id.myPageFrag_weightTxt)
        val birthTxt = binding.root.findViewById<TextView>(R.id.myPageFrag_birthTxt)
        val genderTxt = binding.root.findViewById<TextView>(R.id.myPageFrag_genderTxt)
        val subTxt = binding.root.findViewById<TextView>(R.id.myPageFrag_subTxt)

        val userInfo = SnsDao.getInstance().snsGetMember(MemberSingleton.id.toString())
        Log.d("AccountFragment", "#21# 마이페이지 회원정보 가져온 값 > ${userInfo.toString()}")

        if (userInfo != null) {
            idTxt.text = userInfo.id
            nicknameTxt.text = userInfo.nickname
            nameTxt.text = userInfo.name
            emailTxt.text = userInfo.email
            phoneTxt.text = userInfo.phone
            heightTxt.text = "${userInfo.height}cm"
            weightTxt.text = "${userInfo.weight}kg"
            birthTxt.text = userInfo.birth

            if (userInfo.gender == "M"){
                genderTxt.text = "남자"
            } else if(userInfo.gender == "W"){
                genderTxt.text = "여자"
            }

            if (userInfo.subscribe == 1) {
                subTxt.text = "구독 O"
            } else if (userInfo.subscribe == 0) {
                subTxt.text = "구독 X"
            }
        }

        /* #21# 회원정보 수정 Button 클릭 시 이동 */
        val userUpdateBtn = binding.root.findViewById<Button>(R.id.myPageFrag_updateBtn)
        userUpdateBtn.setOnClickListener { startActivity(Intent(activity, MemberUpdateActivity::class.java)) }

        return binding.root
    }
}