package com.metamom.bbssample.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.metamom.bbssample.FoodListMeals.FoodListMeals

import com.metamom.bbssample.R
import com.metamom.bbssample.databinding.FragmentHomeBinding
import com.metamom.bbssample.sns.SnsActivity
import com.metamom.bbssample.subscribe.SubAddActivity
import com.metamom.bbssample.subscribe.SubInfoActivity
import com.metamom.bbssample.subsingleton.MemberSingleton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        /* #21# '구독 신청' Button 클릭 시 구독 여부 확인
         *   case_1) 구독일 경우 구독 정보 출력 페이지로 이동
         *   case_2) 구독이 아닐경우 구독 신청 페이지로 이동 */
        binding.mainSubBtn.setOnClickListener {

            if(MemberSingleton.subscribe == "0") {          // case_2) 구독이 아닐 경우
                val i = Intent(context, SubAddActivity::class.java)
                startActivity(i)
            }
            else {                                          // case_1) 구독일 경우
                Toast.makeText(context, "구독 회원입니다! 😉", Toast.LENGTH_LONG).show()

                val i = Intent(context,  SubInfoActivity::class.java)
                startActivity(i)
            }
        }

        //sns 이동 버튼
        binding.SnsBtn.setOnClickListener {
            val intent = Intent(context, SnsActivity::class.java)
            startActivity(intent)
        }

        binding.haebinBtn.setOnClickListener {
            val i = Intent(context, FoodListMeals::class.java)
            startActivity(i)
        }
        // TODO 'kakaoLogout'버튼 다른곳에 숨겨서 배치하기
/*
        binding.kakaoLogoutBtn.setOnClickListener {
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Log.e("MainButtonActivity", "로그아웃 실패")
                }else {
                    Log.e("MainButtonActivity", "로그아웃 성공")
                }

                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            }
        }
*/

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_self)
        }

        binding.talkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_talkFragment)
        }

        /* #21# [구독] 오늘의 식단
        *  case_1) 구독일 경우 > MealFragment로 이동
        *  case_2) 구독이 아닐 경우 > HomeFragment로 이동 */
        binding.mealTap.setOnClickListener {

            if (MemberSingleton.subscribe == "0") {          // case_2) 구독이 아닐 경우
                val builder = AlertDialog.Builder(activity)
                builder.setTitle("오늘의 식단")
                builder.setMessage("구독회원 전용 서비스 입니다 😥")
                builder.show()

                it.findNavController().navigate(R.id.action_homeFragment_self)
            }
            else {                                          // case_1) 구독일 경우
                it.findNavController().navigate(R.id.action_homeFragment_to_mealFragment)
            }
        }

        binding.recipeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_recipeFragment)
        }

        /* #21# 마이페이지 */
        binding.accountTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_accountFragment)
        }

        return binding.root
    }
}