package com.metamom.bbssample.subscribe

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.metamom.bbssample.MainButtonActivity
import com.metamom.bbssample.R

class SubTodayMealsMorning : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_today_meals_morning)

        /* #21# 뒤로가기(이전화면) */
        val previousBtn = findViewById<ImageButton>(R.id.subMeal_PreBtn)
        previousBtn.setOnClickListener {
            val i = Intent(this, SubMyMealsActivity::class.java)
            startActivity(i)
        }


        /* #21# 다이어트 식단 recyclerView 드로잉 */
        var recyclerView = findViewById<RecyclerView>(R.id.subMorning_recyclerView)

        /* #21# Back과 통신하여 다이어트 식단 가져오기 (random) */
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 사용자가 신청한 유형에 따라 식단을 줘야함 다이어트 or 운동 > 넘겨줄때 유형파악 필요
        val todayDietMeal = SubscribeDao.getInstance().subRandomDietMeal(SubTodayMealDto(0, 0, "", "", 0, 0))         // subdfTime 0 = 아침
        if (todayDietMeal != null){
            Log.d("SubTodayMealsMorning", "#21# Back으로부터 전달받은 오늘의 다이어트 식단 > ${todayDietMeal.toString()}")

            val subCustomAdapter = SubCustomAdapter(this, todayDietMeal)
            recyclerView.adapter = subCustomAdapter

            val layout = LinearLayoutManager(this)      // 세로모드
            recyclerView.layoutManager = layout
            recyclerView.setHasFixedSize(true)
        }
        else {
            val i = Intent(this, SubMyMealsActivity::class.java)
            startActivity(i)

            Toast.makeText(this, "관리자에게 문의해주시길 바랍니다. 죄송합니다", Toast.LENGTH_LONG).show()
            /*val builder = AlertDialog.Builder(this);
            builder.setTitle("❗")
            builder.setMessage("관리자에게 문의해주시길 바랍니다. 죄송합니다")
            builder.show()*/
        }

    }
}