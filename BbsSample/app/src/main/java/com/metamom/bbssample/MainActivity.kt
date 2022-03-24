package com.metamom.bbssample

import com.metamom.bbssample.FoodListMeals.FoodListMeals
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val insertMemberBtn = findViewById<TextView>(R.id.insertMemberBtn)

        val snsBtn = findViewById<Button>(R.id.SnsBtn)
        val haebinBtn = findViewById<Button>(R.id.haebinBtn)
        id = findViewById(R.id.editId)
        pwd = findViewById(R.id.editPwd)
        loginBtn = findViewById(R.id.loginBtn)
        kakaoBtn = findViewById(R.id.kakaoBtn)

        // #21# Login 버튼 클릭 시 main button 페이지로 이동
        loginBtn.setOnClickListener {

            val userId = id.text.toString()
            val userPwd = pwd.text.toString()

            var dto = MemberDao.getInstance()
                .login(
                    MemberDto(userId, userPwd, "", "",
                        "", "", "", 0.0,
                        0.0, "n", 0, 0, "",
                        0.0, "")
                )

            /* #21# [for 구독여부 판단, test용] Login Button 클릭 시 현재 로그인한 사용자의 정보를 MemberSingleton에 저장 */
            MemberSingleton.id = "123"
            MemberSingleton.subscribe = "1"             // 1 = 구독
            //MemberSingleton.subscribe = "0"          // 0 = 비구독
            Log.d("MainActivity", "#21# 현재 로그인한 사용자의 정보(MemberSingleton) ${MemberSingleton.toString()}")

            if (dto != null) {
                MemberDao.user = dto

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
            }
            else if (tokenInfo != null) {
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
            }
            else if (token != null) {
                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainButtonActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }

        haebinBtn.setOnClickListener {
            val i = Intent(this, FoodListMeals::class.java)
            startActivity(i)
        kakaoBtn.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            }else{
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }

    // 아이디 저장
    private fun saveData() {



    }
}

