package com.metamom.bbssample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class SearchPwd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_pwd)

        val editId2 = findViewById<EditText>(R.id.editId2)
        val editName2 = findViewById<EditText>(R.id.editName2)
        val editBrith2 = findViewById<EditText>(R.id.editBirth2)
        val searchPwdBtn = findViewById<Button>(R.id.searchPwdBtn)
        val resultText2 = findViewById<TextView>(R.id.resultText2)
        val imageView3 = findViewById<ImageView>(R.id.imageView3)

        searchPwdBtn.setOnClickListener {

            var pwd = MemberDao.getInstance().searchPwd(MemberDto(
                editId2.text.toString(), "", editName2.text.toString(), "",
                "", "", "", 0.0,
                0.0, "n", 0, 0, editBrith2.text.toString(),
                0.0, ""
            )).toString()
            if(pwd == null || pwd == ""){
                Toast.makeText(this,"정보를 확인하고 다시 입력해주세요",Toast.LENGTH_LONG).show()
            }else {
                resultText2.text = "${editName2.text} 님의 비밀번호는 " + pwd + "입니다."
            }
        }
        imageView3.setOnClickListener {
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }
    }
}