package com.metamom.bbssample.subscribe

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import com.metamom.bbssample.MainButtonActivity
import com.metamom.bbssample.R
import com.metamom.bbssample.subsingleton.MemberSingleton
import com.metamom.bbssample.subsingleton.SubTodayMealSingleton

class SubMyMealsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_my_meals)

        /* #21# 뒤로가기(이전화면) */
        val previousBtn = findViewById<ImageButton>(R.id.subMeal_PreBtn)
        previousBtn.setOnClickListener {
            val i = Intent(this, MainButtonActivity::class.java)
            startActivity(i)
        }


        /* #21# 추천한 식단을 기록하고 있는 REMEMBER TABLE에서 2일 초과인 식단 확인 및 제거
        *  ex) 오늘이 22.03.29일 경우 → 26일 이하 식단은 제거 (27, 28일 식단만 남겨둠) */
        var delRememberMeal = SubscribeDao.getInstance().subRememberDel(MemberSingleton.id.toString())
        Log.d("SubMyMealsActivity", "#21# REMEMBER TABLE 내 3일이상 식단 제거 > ${delRememberMeal}개")



        /* #21# 구독 신청 시간에 따라 동적 Button 생성 */
        var linearLayout = findViewById<LinearLayout>(R.id.subMeal_linearLayout)
        var childLayout :LinearLayout? = null
        var btnCount = 0                            // 동적 Button 생성 개수를 저장할 변수

        var subInfo = SubscribeDao.getInstance().getSubInfo(MemberSingleton.id.toString())  // 구독 정보 가져오기

        // 1) 회원이 신청한 구독 시간 text값 List에 저장
        if (subInfo != null) {
            btnCount = subInfo.subMorning + subInfo.subLunch + subInfo.subDinner + subInfo.subSnack
            Log.d("SubMyMealsActivity", "#21# 구독 정보에서 가져온 구독 시간개수 > ${btnCount}개")

            var mealTimeTxt = mutableListOf<String>()
            if (subInfo.subMorning == 1){
                mealTimeTxt.add("아침")
            }
            if (subInfo.subLunch == 1){
                mealTimeTxt.add("점심")
            }
            if (subInfo.subDinner == 1){
                mealTimeTxt.add("저녁")
            }
            if (subInfo.subSnack == 1){
                mealTimeTxt.add("간식")
            }
            Log.d("SubMyMealsActivity", "#21# 구독 정보에서 가져온 구독 시간 text값 > $mealTimeTxt")


            // 2) 현재 구독자의 구독시간에 따라 Button 생성(아침/점심/저녁/간식)
            for (i in 0 until btnCount){
                // Layout 만들기 __button을 2개 만들었다면 다음 줄로
                if (i % 2 == 0){
                    childLayout = LinearLayout(this)
                    childLayout.orientation = LinearLayout.HORIZONTAL
                    val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 305)
                    childLayout.layoutParams = layoutParams
                }

                // Button Layout 설정
                var btnParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                btnParams.weight = 1.0f
                btnParams.setMargins(1, 1, 1, 1)

                // Button 생성 (+ Button 스타일 지정)
                val timeBtn = android.widget.Button(this).apply {
                    text = mealTimeTxt[i]
                    layoutParams = btnParams
                    setBackgroundColor(Color.rgb(106,0,244))                                // background 색
                    setTextColor(Color.rgb(255,255,255))                                    // button text 색
                    paint.typeface = Typeface.createFromAsset(assets, "font_content_bold_ass.otf")    // 글꼴 font 적용 __main 폴더 > assets 폴더 안에 있는 font 적용
                    textSize = 20F
                    id = i

                    // Button 클릭 시 반응 __해당 시간에 맞는 Activity로 이동
                    setOnClickListener {
                        Log.d("SubMyMealsActivity", "#21# 선택한 식단 시간 Button값 > ${mealTimeTxt[i]}")
                        moveMealsView(mealTimeTxt[i])
                    }
                }
                childLayout?.addView(timeBtn)

                if (i % 2 == 1 || i == (btnCount - 1)) {
                    linearLayout.addView(childLayout)
                }
            }

        }
        else {
            Toast.makeText(this, "구독 서비스 입니다.", Toast.LENGTH_LONG).show()

            val i = Intent(this, SubAddActivity::class.java)
            startActivity(i)
        }
    }




    fun moveMealsView(time :String) {
        when (time) {
            "아침" -> {
                val i = Intent(this, SubTodayMealsMorning::class.java)
                SubTodayMealSingleton.time = 0
                startActivity(i)
            }
            "점심" -> {
                val i = Intent(this, SubTodayMealsLunch::class.java)
                SubTodayMealSingleton.time = 1
                startActivity(i)
            }
            "저녁" -> {
                val i = Intent(this, SubTodayMealsDinner::class.java)
                SubTodayMealSingleton.time = 2
                startActivity(i)
            }
            "간식" -> {
                val i = Intent(this, SubTodayMealsSnack::class.java)
                SubTodayMealSingleton.time = 3
                startActivity(i)
            }
            else -> {
                val builder = AlertDialog.Builder(this);
                builder.setTitle("❗")
                builder.setMessage("관리자에게 문의해주시길 바랍니다. 죄송합니다")
                builder.show()
            }
        }
    }
}