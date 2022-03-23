package com.metamom.bbssample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.metamom.bbssample.subscribe.SubAddActivity
import com.metamom.bbssample.subscribe.SubInfoActivity
import com.metamom.bbssample.subscribe.SubMyMealsActivity
import com.metamom.bbssample.subsingleto.MemberSingleton

/* #21# [구독] */
class MainButtonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_button)

        /* 지금은 단순히 MemberSingleton 안에 subscribe가 1/0인지만 확인하고 있다.
        *  - 로그인 시 사용자 id, subscribe 값을 Back으로 전달
        *  - Back은 1) 멤버 table의 해당 id의 사용자의 subscribe 값이 1인지 확인하고, 2) 구독 table의 구독 시작날짜로부터 만료되지 않았는지까지 확인필요
        *  - Back으로 넘겨줄 값 id, 받아올 값 String (판정여부)  */


        /* #21# '구독 신청' Button 클릭 시 구독 여부 확인
        *   case_1) 구독일 경우 구독 정보 출력 페이지로 이동
        *   case_2) 구독이 아닐경우 구독 신청 페이지로 이동 */
        val SubBtn = findViewById<Button>(R.id.main_subBtn)
        SubBtn.setOnClickListener {

            if(MemberSingleton.subscribe == "0") {          // case_2) 구독이 아닐 경우
                val i = Intent(this,  SubAddActivity::class.java)
                startActivity(i)
            }
            else {                                          // case_1) 구독일 경우
                //Toast.makeText(this, "구독 회원입니다! 😉", Toast.LENGTH_LONG).show()
                val i = Intent(this,  SubInfoActivity::class.java)
                startActivity(i)
            }
        }

        /* #21# 식단정보 Button 클릭 */
        val MealBtn = findViewById<Button>(R.id.main_myMealsBtn)
        MealBtn.setOnClickListener {

            if(MemberSingleton.subscribe == "0") {          // case_2) 구독이 아닐 경우
                Toast.makeText(this, "구독 서비스 입니다!", Toast.LENGTH_LONG).show()

                val i = Intent(this,  SubAddActivity::class.java)
                startActivity(i)
            }
            else {                                          // case_1) 구독일 경우
                val i = Intent(this,  SubMyMealsActivity::class.java)
                startActivity(i)

                /* !!!!!!! 구독 만료확인 code 추가하기 */

            }
        }
    }
}