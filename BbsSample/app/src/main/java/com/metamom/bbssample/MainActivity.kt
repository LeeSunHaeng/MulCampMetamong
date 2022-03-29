package com.metamom.bbssample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.metamom.bbssample.sns.SnsActivity
import com.metamom.bbssample.subscribe.SubscribeDao
import com.metamom.bbssample.subscribe.SubscribeDto
import com.metamom.bbssample.subsingleto.MemberSingleton
import com.metamom.bbssample.subsingleton.SubTodayMealSingleton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        // #21# Login 버튼 클릭 시 main button 페이지로 이동
        loginBtn.setOnClickListener {

            /* #21# [for 구독여부 판단, test용] Login 시 현재 로그인한 사용자를 구독 회원여부 판단 진행 후 로그인한 사용자의 정보를 MemberSingleton에 저장
            *        > 만약 구독 만료회원일 경우 == 구독값(subscribe 컬럼) 0으로 변경 + 구독 DB에서 삭제 */
            MemberSingleton.id = "zeze3"
            MemberSingleton.subscribe = "1"                                  // 1 = 구독
            //MemberSingleton.subscribe = "0"                                  // 0 = 비구독
            MemberSingleton.height = 150.4
            MemberSingleton.weight = 42.7
            Log.d("MainActivity", "#21# 현재 로그인한 사용자의 정보(MemberSingleton) ${MemberSingleton.toString()}")

            /* #21# 구독만료 확인 */
            if (MemberSingleton.subscribe == "1"){      // 구독 회원일 경우 구독 만료확인
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
                    }
                }
                else {
                    Log.d("MainActivity", "#21# 로그인 > 구독회원일 경우 > 구독정보 null Error")
                }
            }

            val i = Intent(this,  MainButtonActivity::class.java)
            startActivity(i)
        }


        val insertMemberBtn = findViewById<TextView>(R.id.insertMemberBtn)

        val snsBtn = findViewById<Button>(R.id.SnsBtn)
        val haebinBtn = findViewById<Button>(R.id.haebinBtn)
        /*loginBtn.setOnClickListener {

            val id = editId.text.toString()
            val password = editPw.text.toString()
            println("~~~~~~~~~~$id, $password")
            var dto = MemberDao.getInstance().login(MemberDto(id, password, "", "", 0))
            if(dto != null){
                MemberDao.user = dto

                Toast.makeText(this, "${dto.name}님 환영합니다", Toast.LENGTH_LONG).show()

                 //login 되면 이동
                //val i = Intent(this, BbsListActivity::class.java)

            }else { Toast.makeText(this, "ID나 PW를 확인하세요", Toast.LENGTH_LONG).show()
           }
        }*/

        insertMemberBtn.setOnClickListener {
            Log.d("Main", "#21# 회원가입 동작 Button 확인")
            val i = Intent(this, InsertActivity::class.java)
            startActivity(i)
        }

        //sns 이동 버튼
        snsBtn.setOnClickListener {
            println("snsBtn 클릭!")
            val intent = Intent(this, SnsActivity::class.java)
            startActivity(intent)
        }

        haebinBtn.setOnClickListener {
            Log.d("Main", "#21# 해빈님 동작 Button 확인")
            val i = Intent(this, Food_List_Meals::class.java)
            startActivity(i)
        }
    }
}
// yes

