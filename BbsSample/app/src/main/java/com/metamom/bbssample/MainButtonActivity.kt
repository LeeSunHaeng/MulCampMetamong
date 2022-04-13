package com.metamom.bbssample

import android.app.Activity
import android.app.AlertDialog
import android.app.Fragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import com.metamom.bbssample.sns.SnsDao
import com.metamom.bbssample.subscribe.*
import com.metamom.bbssample.subsingleton.MemberSingleton
import com.metamom.bbssample.subsingleton.SubTodayMealSingleton
import androidx.appcompat.widget.SearchView
import com.metamom.bbssample.fragments.TalkFragment
import com.metamom.bbssample.subsingleton.SnsSingleton
import kotlinx.android.synthetic.main.activity_main_button.*

class MainButtonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_button)
        val home = findViewById<View>(R.id.fragmentContainerView)

        /* #21# 구독여부 판단 & 구독만료 판단 */
        // 1) 현재 로그인한 사용자의 정보 MemberSingleton에 저장 > 저장할 정보 [ID, 구독값, 키, 몸무게]
        Log.d("MainButtonActivity", "#21# 현재 로그인한 사용자 ID 확인 (MemberSingleton) > ${MemberSingleton.id}")
        val userInfo = SnsDao.getInstance().snsGetMember(MemberSingleton.id.toString())
        if (userInfo != null) {
            MemberSingleton.subscribe = userInfo.subscribe.toString()
            MemberSingleton.height = userInfo.height
            MemberSingleton.weight = userInfo.weight
        } else {
            Log.d("MainButtonActivity", "#21# 현재 로그인한 사용자 정보 가져오기 실패 Error")
        }

        // 2) 구독회원(subscribe = 1)일 경우 구독만료 확인
        if (MemberSingleton.subscribe == "1"){
            // 1) 현재 로그인한 사용자의 구독 정보 가져오기 (subInfo)
            var subInfo = SubscribeDao.getInstance().getSubInfo(MemberSingleton.id.toString())
            Log.d("MainButtonAcitvity", "#21# MainButtonAcitvity 현재 로그인한 사용자의 구독정보 > ${subInfo.toString()}")

            // 2) 1번에서 가져온 구독정보를 사용하여 구독만료 확인 (subEndCheck)
            if (subInfo != null){
                var subEndCheck = SubscribeDao.getInstance().subEnddayCheck(SubscribeDto(subInfo.subId, subInfo.subType, subInfo.subPeriod, 0, 0, 0, 0, subInfo.subStartday, subInfo.subEndday))
                Log.d("MainButtonAcitvity", "#21# MainButtonAcitvity 현재 로그인한 사용자의 구독만료 Check > $subEndCheck")

                // 2-i) Success인 경우 == 구독만료 X, 회원의 구독정보를 Singleton에 저장
                if (subEndCheck == "Success"){
                    SubTodayMealSingleton.type = subInfo.subType                        // 구독 유형(다이어트 or 운동)

                    // 사용자 키/몸무게에 따른 하루 권장 칼로리 계산  == ((자신의 키 - 100) * 0.9) * 활동지수(30으로 고정)  &  !!소수점 2째자리까지만 포함(반올림X, 자르기o)
                    var totalKcal = String.format("%.2f", (((MemberSingleton.height!! - 100) * 0.9) * 30)).toDouble()
                    Log.d("MainButtonActivity", "#21# 하루 권장 칼로리 > $totalKcal")

                    // 하루 권장 칼로리를 아침, 점심, 저녁, 간식 칼로리 비율로 저장 (아 15%, 점 50%, 저 20%, 간 15%)
                    SubTodayMealSingleton.morningKcal = String.format("%.2f", totalKcal * 15 / 100).toDouble()
                    SubTodayMealSingleton.lunchKcal = String.format("%.2f", totalKcal * 50 / 100).toDouble()
                    SubTodayMealSingleton.dinnerKcal = String.format("%.2f", totalKcal * 20 / 100).toDouble()
                    SubTodayMealSingleton.snackKcal = String.format("%.2f", totalKcal * 15 / 100).toDouble()

                    Log.d("MainButtonActivity", "#21# 구독회원 정보 SubTodayMealSingleton 확인 > ${SubTodayMealSingleton.toString()}")
                }
                // 2-ii) SuccessEnd인 경우 == 구독만료
                if (subEndCheck == "SuccessEnd"){
                    Log.d("MainButtonActivity", "#21# 구독 만료임에 따라 멤버 TABLE 구독값 수정 & 구독 TABLE 내 삭제")

                    /* !!!! 회원정보 다시 가져와서 MemberSingleton에 넣기 _구독만료로 구독값 변경했으니까 */
                    val userInfo = SnsDao.getInstance().snsGetMember(MemberSingleton.id.toString())
                    if (userInfo != null) {
                        MemberSingleton.subscribe = userInfo.subscribe.toString()
                    } else {
                        Log.d("MainButtonActivity", "#21# (구독만료 후) 현재 로그인한 사용자 정보 가져오기 실패 Error")
                    }

                    // 구독만료 안내 알림창 
                    val builder = AlertDialog.Builder(this);
                    builder.setTitle("구독 만료")
                    builder.setMessage("신청하였던 구독 기간이 만료되었습니다. 감사합니다 😌")
                    builder.show()
                }
            }
            else {
                Log.d("MainButtonActivity", "#21# 로그인 > 구독회원일 경우 > 구독정보 null Error 발생")
            }
        }
        else if (MemberSingleton.subscribe == "0") {
            Log.d("MainButtonActivity", "#21# 현재 로그인한 사용자는 구독회원이 아님")
        }
    }

   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

       if(resultCode == Activity.RESULT_OK){
           when(requestCode){
               400->{
                   SnsSingleton.position = data?.getSerializableExtra("position") as Int
                   println("~~~~~~~~~${SnsSingleton.position}~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~400~~~~~~~~~~~~~~~~~~~~~~~~~~")
                   TalkFragment().adaptered.update(SnsSingleton.position!!)

               }

               500->{
                   println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~500~~~~~~~~~~~~~~~~~~~~~~~~~~")
               }

           }
       }

    }
}