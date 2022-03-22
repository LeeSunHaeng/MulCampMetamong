package com.metamom.bbssample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var id: EditText
    lateinit var pwd: EditText
    lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id = findViewById(R.id.editId)
        pwd = findViewById(R.id.editPwd)
        loginBtn = findViewById(R.id.loginBtn)

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
            MemberSingleton.id = "zeze"
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

        val insertMemberBtn = findViewById<TextView>(R.id.insertMemberBtn)

        insertMemberBtn.setOnClickListener {
            val i = Intent(this, InsertActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }
    }
}

