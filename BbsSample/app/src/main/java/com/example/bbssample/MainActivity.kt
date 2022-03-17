package com.example.bbssample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editId = findViewById<EditText>(R.id.editId)
        val editPw = findViewById<EditText>(R.id.editPw)
        val nums = 10
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val snsBtn = findViewById<Button>(R.id.SnsBtn)
        val haebinBtn = findViewById<Button>(R.id.haebinBtn)
        loginBtn.setOnClickListener {

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
        }
        //sns 이동 버튼
        snsBtn.setOnClickListener {
            val intent = Intent(this, SnsActivity::class.java)
            startActivity(intent)
        }

        haebinBtn.setOnClickListener {
            val i = Intent(this, Food_List_Meals::class.java)
            startActivity(i)
         }
    }
}

