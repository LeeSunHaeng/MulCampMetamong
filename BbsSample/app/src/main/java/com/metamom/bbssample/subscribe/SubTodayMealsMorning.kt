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


        /* #21# 오늘의 식단 recyclerView 드로잉
        *  1) 사용자의 구독 유형을 파악 > 다이어트 or 운동
        *  2) 유형에 따라 Back과 통신하여 식단을 random으로 가져온다.
        *  3) RecyclerView에 드로잉
        *  4) 식단 추천 성공 시 추천한 식단은 DB - REMEMBER TABLE 내 저장(추가) */
        var recyclerView = findViewById<RecyclerView>(R.id.subMorning_recyclerView)

        /* #21# 1) Back과 통신하여 다이어트 식단 가져오기 (random) */
        Log.d("SubTodayMealsMorning", "#21# SubTodayMealSingleton 확인 > ${SubTodayMealSingleton.toString()}")
        var todayMeal: Any? = null

        // 1-1) 회원이 다이어트 식단을 신청하였을 경우 (type == 0)
        if (SubTodayMealSingleton.type == 0) {
            Log.d("SubTodayMealsMorning", "#21# 오늘의 식단[아침] *다이어트 식단* 가져오기 실행")
            todayMeal = SubscribeDao.getInstance().subRandomDietMeal(SubDietMealDto(0, SubTodayMealSingleton.time!!, "", "", SubTodayMealSingleton.morningKcal!!.toDouble(), 0, SubTodayMealSingleton.type.toString()))
            // 1-4) 추천한 식단 정보 DB TABLE에 저장하기
            if (todayMeal != null) {
                val rememberMeal = SubscribeDao.getInstance().subMealRemember(SubMealRememberDto(todayMeal.subdfSeq, todayMeal.subdfName, MemberSingleton.id, "", todayMeal.subdfTime,SubTodayMealSingleton.type!!))
                if (rememberMeal == "Success") Log.d("SubTodayMealsMorning", "#21# 추천한 다이어트 식단 REMEMBER TABLE 내 저장완료")
            }
        }
        // 1-2) 회원이 운동 식단을 신청하였을 경우 (type == 1)
        else if (SubTodayMealSingleton.type == 1) {
            Log.d("SubTodayMealsMorning", "#21# 오늘의 식단[아침] *운동 식단* 가져오기 실행")
            todayMeal = SubscribeDao.getInstance().subRandomExerMeal(SubExerMealDto(0, SubTodayMealSingleton.time!!, "", "", SubTodayMealSingleton.morningKcal!!.toDouble(), 0, SubTodayMealSingleton.type.toString()))
            // 1-4) 추천한 식단 정보 DB TABLE에 저장하기
            if (todayMeal != null) {
                val rememberMeal = SubscribeDao.getInstance().subMealRemember(SubMealRememberDto(todayMeal.subefSeq, todayMeal.subefName, MemberSingleton.id, "", todayMeal.subefTime,SubTodayMealSingleton.type!!))
                if (rememberMeal == "Success") Log.d("SubTodayMealsMorning", "#21# 추천한 운동 식단 REMEMBER TABLE 내 저장완료")
            }
        }

        // 1-3) 전달받은 식단을 RecyclerView에 드로잉하기
        if (todayMeal != null){
            Log.d("SubTodayMealsMorning", "#21# Back으로부터 전달받은 오늘의 식단 > ${todayMeal.toString()}")

            val subCustomAdapter = SubCustomAdapter(this, todayMeal)
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