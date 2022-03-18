package com.example.bbssample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import java.lang.reflect.Member

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        // #21# Login 버튼 클릭 시 main button 페이지로 이동
        loginBtn.setOnClickListener {

            /* #21# [for 구독여부 판단, test용] Login Button 클릭 시 현재 로그인한 사용자의 정보를 MemberSingleton에 저장 */
            MemberSingleton.id = "zeze"
            MemberSingleton.subscribe = "1"             // 1 = 구독
            //MemberSingleton.subscribe = "0"          // 0 = 비구독
            Log.d("MainActivity", "#21# 현재 로그인한 사용자의 정보(MemberSingleton) ${MemberSingleton.toString()}")


            val i = Intent(this,  MainButtonActivity::class.java)
            startActivity(i)
        }
    }
}