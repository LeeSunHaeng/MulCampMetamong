package com.metamom.bbssample

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

import android.widget.Toast
import com.kakao.sdk.user.UserApiClient
import com.metamom.bbssample.FoodListMeals.FoodListMeals
import com.metamom.bbssample.sns.SnsActivity
import com.metamom.bbssample.sns.SnsDao
import com.metamom.bbssample.subscribe.*
import com.metamom.bbssample.subsingleton.MemberSingleton
import com.metamom.bbssample.subsingleton.SubTodayMealSingleton


/* #21# [구독] */
class MainButtonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_button)

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
            // 2) 1번에서 가져온 구독정보를 사용하여 구독만료 확인 (subEndCheck)
            if (subInfo != null){
                var subEndCheck = SubscribeDao.getInstance().subEnddayCheck(SubscribeDto(subInfo.subId, subInfo.subType, subInfo.subPeriod, 0, 0, 0, 0, subInfo.subStartday, subInfo.subEndday))

                // 2-i) Success인 경우 == 구독만료 X, 회원의 구독정보를 Singleton에 저장
                if (subEndCheck == "Success"){
                    SubTodayMealSingleton.type = subInfo.subType                        // 구독 유형(다이어트 or 운동)

                    // 사용자 키/몸무게에 따른 하루 권장 칼로리 계산  == ((자신의 키 - 100) * 0.9) * 활동지수(30으로 고정)  &  !!소수점 2째자리까지만 포함(반올림X, 자르기o)
                    var totalKcal = String.format("%.2f", (((MemberSingleton.height!! - 100) * 0.9) * 30)).toDouble()
                    Log.d("MainActivity", "#21# 하루 권장 칼로리 > $totalKcal")

                    // 하루 권장 칼로리를 아침, 점심, 저녁, 간식 칼로리 비율로 저장 (아 15%, 점 50%, 저 20%, 간 15%)
                    SubTodayMealSingleton.morningKcal = String.format("%.2f", totalKcal * 15 / 100).toDouble()
                    SubTodayMealSingleton.lunchKcal = String.format("%.2f", totalKcal * 50 / 100).toDouble()
                    SubTodayMealSingleton.dinnerKcal = String.format("%.2f", totalKcal * 20 / 100).toDouble()
                    SubTodayMealSingleton.snackKcal = String.format("%.2f", totalKcal * 15 / 100).toDouble()

                    Log.d("MainActivity", "#21# 구독회원 정보 SubTodayMealSingleton 확인 > ${SubTodayMealSingleton.toString()}")
                }
                // 2-ii) SuccessEnd인 경우 == 구독만료
                if (subEndCheck == "SuccessEnd"){
                    Log.d("MainActivity", "#21# 구독 만료임에 따라 멤버 TABLE 구독값 수정 & 구독 TABLE 내 삭제")

                    /* !!!! 회원정보 다시 가져와서 MemberSingleton에 넣기 _구독만료로 구독값 변경했으니까 */
                    val userInfo = SnsDao.getInstance().snsGetMember(MemberSingleton.id.toString())
                    if (userInfo != null) {
                        MemberSingleton.subscribe = userInfo.subscribe.toString()
                    } else {
                        Log.d("MainButtonActivity", "#21# (구독만료 후) 현재 로그인한 사용자 정보 가져오기 실패 Error")
                    }
                }
            }
            else {
                Log.d("MainActivity", "#21# 로그인 > 구독회원일 경우 > 구독정보 null Error 발생")
            }
        }


        /* #21# '구독 신청' Button 클릭 시 구독 여부 확인
        *   case_1) 구독일 경우 구독 정보 출력 페이지로 이동
        *   case_2) 구독이 아닐경우 구독 신청 페이지로 이동 */
        val SubBtn = findViewById<Button>(R.id.main_subBtn)
        SubBtn.setOnClickListener {

            if (MemberSingleton.subscribe == "0") {          // case_2) 구독이 아닐 경우
                val i = Intent(this, SubAddActivity::class.java)
                startActivity(i)
            } else {                                          // case_1) 구독일 경우
                //Toast.makeText(this, "구독 회원입니다! 😉", Toast.LENGTH_LONG).show()
                val i = Intent(this, SubInfoActivity::class.java)
                startActivity(i)
            }
        }

        /* #21# 식단정보 Button 클릭 */
        val MealBtn = findViewById<Button>(R.id.main_myMealsBtn)
        MealBtn.setOnClickListener {

            if (MemberSingleton.subscribe == "0") {          // case_2) 구독이 아닐 경우
                Toast.makeText(this, "구독 서비스 입니다!", Toast.LENGTH_LONG).show()

                val i = Intent(this, SubAddActivity::class.java)
                startActivity(i)
            } else {                                          // case_1) 구독일 경우
                val i = Intent(this, SubMyMealsActivity::class.java)
                startActivity(i)
            }
        }



        val snsBtn = findViewById<Button>(R.id.SnsBtn)
        val haebinBtn = findViewById<Button>(R.id.haebinBtn)

        //sns 이동 버튼
        snsBtn.setOnClickListener {
            val intent = Intent(this, SnsActivity::class.java)
            startActivity(intent)
        }

        haebinBtn.setOnClickListener {
            val i = Intent(this, FoodListMeals::class.java)
            startActivity(i)
        }

        val kakaoLogoutBtn = findViewById<Button>(R.id.kakaoLogoutBtn)

        kakaoLogoutBtn.setOnClickListener {
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Toast.makeText(this, "로그아웃 실패 $error", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show()
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
            }
        }
    }
}