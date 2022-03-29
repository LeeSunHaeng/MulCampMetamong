package com.metamom.bbssample

import com.metamom.bbssample.FoodListMeals.FoodListMeals
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import android.widget.*
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import com.kakao.util.helper.Utility
import com.metamom.bbssample.subsingleton.MemberSingleton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var id: EditText
    lateinit var pwd: EditText
    lateinit var loginBtn: Button
    lateinit var kakaoBtn: TextView
    lateinit var idCheck: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id = findViewById(R.id.editId)
        pwd = findViewById(R.id.editPwd)
        loginBtn = findViewById(R.id.loginBtn)
        kakaoBtn = findViewById(R.id.kakaoBtn)

        // #21# Login 버튼 클릭 시 main button 페이지로 이동
        loginBtn.setOnClickListener {

            /* #21# [for 구독여부 판단, test용] Login 시 현재 로그인한 사용자를 구독 회원여부 판단 진행 후 로그인한 사용자의 정보를 MemberSingleton에 저장
            *        > 만약 구독 만료회원일 경우 == 구독값(subscribe 컬럼) 0으로 변경 + 구독 DB에서 삭제 */
            MemberSingleton.id = "zeze3"
            MemberSingleton.subscribe = "1"                                  // 1 = 구독
            //MemberSingleton.subscribe = "0"                                  // 0 = 비구독
            MemberSingleton.height = 150.4
            MemberSingleton.weight = 42.7
            /*MemberSingleton.id = "zeze4"
            MemberSingleton.subscribe = "1"                                  // 1 = 구독
            MemberSingleton.height = 189.1
            MemberSingleton.weight = 87.3*/
            /*MemberSingleton.id = "zeze6"
            MemberSingleton.subscribe = "1"                                  // 1 = 구독
            MemberSingleton.height = 165.2
            MemberSingleton.weight = 42.1*/
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
            val userId = id.text.toString()
            val userPwd = pwd.text.toString()

            var dto = MemberDao.getInstance()
                .login(
                    MemberDto(
                        userId, userPwd, "", "",
                        "", "", "", 0.0,
                        0.0, "n", 0, 0, "",
                        0.0, ""
                    )
                )

            /* #21# [for 구독여부 판단, test용] Login Button 클릭 시 현재 로그인한 사용자의 정보를 MemberSingleton에 저장 */
            MemberSingleton.id = dto?.id
            MemberSingleton.subscribe = "1"             // 1 = 구독
            //MemberSingleton.subscribe = "0"          // 0 = 비구독
            Log.d("MainActivity", "#21# 현재 로그인한 사용자의 정보(MemberSingleton) ${MemberSingleton.toString()}")

            if (dto != null) {
                MemberDao.user = dto
                MemberSingleton.id = dto.id
                Toast.makeText(this, "${dto.id}님 환영합니다", Toast.LENGTH_LONG).show()
                // login 되면 이동
                val i = Intent(this, MainButtonActivity::class.java)
                startActivity(i)
            } else {
                Toast.makeText(this, "ID나 PW를 확인하세요", Toast.LENGTH_LONG).show()
            }
        }


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

        val insertMemberBtn = findViewById<TextView>(R.id.insertMemberBtn)
        insertMemberBtn.setOnClickListener {

            val i = Intent(this, InsertActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
            startActivity(i)
        }

        // 카카오 키해시 확인
        var keyHash = Utility.getKeyHash(this)
        println("keyhash : " + keyHash)

        // 카카오 로그인
        // 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Log.e("MainActivity", "토큰 정보 보기 실패")
            } else if (tokenInfo != null) {
                Log.e("MainActivity", "토큰 정보 보기 성공")
                val intent = Intent(this, MainButtonActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (token != null) {
                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainButtonActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }

            kakaoBtn.setOnClickListener {
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                    UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                }
            }


        // 아이디 저장
        /*private fun saveData() {



    }*/
    }

}

