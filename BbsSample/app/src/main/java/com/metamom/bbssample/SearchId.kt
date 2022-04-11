package com.metamom.bbssample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class SearchId : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_id)

        val editName = findViewById<EditText>(R.id.editName)
        val editBirth = findViewById<EditText>(R.id.editBirth)
        val searchIdBtn = findViewById<Button>(R.id.searchIdBtn)
        val resultText = findViewById<TextView>(R.id.resultText)
        val imageView2 = findViewById<ImageView>(R.id.imageView2)


        searchIdBtn.setOnClickListener {
                val id = MemberDao.getInstance().searchId(MemberDto(
                    "", "", editName.text.toString(), "",
                    "", "", "", 0.0,
                    0.0, "n", 0, 0, editBirth.text.toString(),
                    0.0, ""
                ))
            println("~~~~~~~~~~~~~~~~~~~~~~~id:$id")
            if(id == null || id == "") {
                Toast.makeText(this,"정보를 확인하고 다시 입력해주세요", Toast.LENGTH_LONG).show()
            }else {
                resultText.text = "${editName.text} 님의 아이디는 " + id + "입니다."
            }
        }
        imageView2.setOnClickListener {
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }

    }
}