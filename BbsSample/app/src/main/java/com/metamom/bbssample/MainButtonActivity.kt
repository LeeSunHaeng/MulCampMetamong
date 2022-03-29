package com.metamom.bbssample

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import android.widget.Toast
import com.kakao.sdk.user.UserApiClient
import com.metamom.bbssample.FoodListMeals.FoodListMeals
import com.metamom.bbssample.sns.SnsActivity
import com.metamom.bbssample.subscribe.SubAddActivity
import com.metamom.bbssample.subscribe.SubInfoActivity
import com.metamom.bbssample.subsingleton.MemberSingleton


/* #21# [구독] */
class MainButtonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_button)


        /* '구독 신청' Button 클릭 시 구독 여부 확인
        *   case_1) 구독일 경우 구독 정보 출력 페이지로 이동
        *   case_2) 구독이 아닐경우 구독 신청 페이지로 이동 */
        val SubBtn = findViewById<Button>(R.id.main_subBtn)
        SubBtn.setOnClickListener {

            if(MemberSingleton.subscribe == "0") {          // case_2) 구독이 아닐 경우
                val i = Intent(this,  SubAddActivity::class.java)
                startActivity(i)
            }
            else {                                          // case_1) 구독일 경우
                Toast.makeText(this, "구독 회원입니다! 😉", Toast.LENGTH_LONG).show()

                val i = Intent(this,  SubInfoActivity::class.java)
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
                }else {
                    Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show()
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
            }
        }
    }
}