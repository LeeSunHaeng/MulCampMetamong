package com.metamom.bbssample.subscribe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.metamom.bbssample.R
import com.metamom.bbssample.subsingleto.MemberSingleton
import com.metamom.bbssample.subsingleton.SubTodayMealSingleton

class SubTodayMealsMorning : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_today_meals_morning)

        /* #21# 뒤로가기(이전화면) */
        val previousBtn = findViewById<ImageButton>(R.id.subMorning_preBtn)
        previousBtn.setOnClickListener {
            val i = Intent(this, SubMyMealsActivity::class.java)
            startActivity(i)
        }

        /* 식단 추천 흐름
        *  1) 식단 추천 이력 확인
        *     Y > 추천하였던 식단 불러와서 RecyclerView에 드로잉
        *     N > 2번으로 이동
        *  2) 구독 유형에 따라 식단을 Random으로 1개 불러와서 RecyclerView에 드로잉
        *  2-1) 불러온 식단 정보는 식단 추천 이력 확인을 위하여 REMEBER TABLE에 저장 */


        var todayMeal: Any? = null
        var recyclerView = findViewById<RecyclerView>(R.id.subMorning_recyclerView)

        /* #21# 오늘의 식단 추천 이력이 있는지 확인 [필요 parms: id, type, time] */
        val checkLogMeal = SubscribeDao.getInstance().subLogCheckMeal(SubMealRememberDto(0, "", MemberSingleton.id, "", SubTodayMealSingleton.time!!, SubTodayMealSingleton.type!!))
        Log.d("", "#21# checkLogMeal 값 확인 > ${checkLogMeal.toString()}")

        // 1) 식단 추천 이력이 있을 경우
        if (checkLogMeal != null){
            /* #21# 추천하였던 식단 불러오기
            *  i) 구독 유형 파악 > 다이어트(0) or 운동(1)
            *  ii) SEQ번호를 Back으로 전달하여 SEQ번호에 해당하는 식단 불러오기
            *  iii) RecyclerView에 드로잉 */
            if (SubTodayMealSingleton.type == 0){           // [다이어트] 식단
                todayMeal = SubscribeDao.getInstance().subDietMeal(checkLogMeal.subremSeq)
            }
            else if (SubTodayMealSingleton.type == 1){      // [운동] 식단
                todayMeal = SubscribeDao.getInstance().subExerMeal(checkLogMeal.subremSeq)
            }
        }
        // 2) 식단 추천 이력이 없을 경우
        else {
            /* #21# 오늘의 식단 추천 및 recyclerView 드로잉 (+ 추천한 식단 저장)
             *  i) 사용자의 구독 유형을 파악 > 다이어트(0) or 운동(1)
             *  ii) Back과 통신하여 식단을 random으로 1개 가져온다.
             *  iii) RecyclerView에 드로잉
             *  iv) 식단 추천 성공 시 추천한 식단은 DB - REMEMBER TABLE 내 저장(추가) */

            /* Back과 통신하여 다이어트 식단 가져오기 (random) */
            Log.d("SubTodayMealsMorning", "#21# SubTodayMealSingleton 확인 > ${SubTodayMealSingleton.toString()}")

            // 2-i) [다이어트] 회원이 다이어트 식단을 신청하였을 경우 (type == 0)
            if (SubTodayMealSingleton.type == 0) {
                Log.d("SubTodayMealsMorning", "#21# 오늘의 식단[아침] *다이어트 식단* 가져오기 실행")
                // 2-ii) 다이어트 식단 가져오기
                todayMeal = SubscribeDao.getInstance().subRandomDietMeal(SubDietMealDto(0, SubTodayMealSingleton.time!!, "", "", SubTodayMealSingleton.morningKcal!!.toDouble(), 0, SubTodayMealSingleton.type.toString()))
                // 2-iv) 추천한 식단 정보 DB TABLE에 저장하기
                if (todayMeal != null) {
                    val rememberMeal = SubscribeDao.getInstance().subMealRemember(SubMealRememberDto((todayMeal as SubDietMealDto).subdfSeq, (todayMeal as SubDietMealDto).subdfName, MemberSingleton.id, "", (todayMeal as SubDietMealDto).subdfTime, SubTodayMealSingleton.type!!))
                    if (rememberMeal == "Success") Log.d("SubTodayMealsMorning", "#21# 추천한 다이어트 식단 REMEMBER TABLE 내 저장완료")
                }
            }
            // 2-i) [운동] 회원이 운동 식단을 신청하였을 경우 (type == 1)
            else if (SubTodayMealSingleton.type == 1) {
                Log.d("SubTodayMealsMorning", "#21# 오늘의 식단[아침] *운동 식단* 가져오기 실행")
                // 2-ii) 운동 식단 가져오기
                todayMeal = SubscribeDao.getInstance().subRandomExerMeal(SubExerMealDto(0, SubTodayMealSingleton.time!!, "", "", SubTodayMealSingleton.morningKcal!!.toDouble(), 0, SubTodayMealSingleton.type.toString()))
                // 2-iv) 추천한 식단 정보 DB TABLE에 저장하기
                if (todayMeal != null) {
                    val rememberMeal = SubscribeDao.getInstance().subMealRemember(SubMealRememberDto((todayMeal as SubExerMealDto).subefSeq, (todayMeal as SubExerMealDto).subefName, MemberSingleton.id, "", (todayMeal as SubExerMealDto).subefTime, SubTodayMealSingleton.type!!))
                    if (rememberMeal == "Success") Log.d("SubTodayMealsMorning", "#21# 추천한 운동 식단 REMEMBER TABLE 내 저장완료")
                }
            }
        }

        // 3) 전달받은 식단을 RecyclerView에 드로잉하기
        if (todayMeal != null){
            Log.d("SubTodayMealsMorning", "#21# Back으로부터 전달받은 오늘의 식단 > ${todayMeal.toString()}")

            val subCustomAdapter = SubCustomAdapter(this, todayMeal!!)
            recyclerView.adapter = subCustomAdapter

            val layout = LinearLayoutManager(this)      // 세로모드
            recyclerView.layoutManager = layout
            recyclerView.setHasFixedSize(true)
        }
        else {
            val i = Intent(this, SubMyMealsActivity::class.java)
            startActivity(i)

            Toast.makeText(this, "관리자에게 문의해주시길 바랍니다. 죄송합니다", Toast.LENGTH_LONG).show()
        }
    }
}