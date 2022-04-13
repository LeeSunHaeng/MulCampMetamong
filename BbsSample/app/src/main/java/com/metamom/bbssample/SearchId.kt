package com.metamom.bbssample

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.metamom.bbssample.databinding.ActivitySearchIdBinding
import kotlinx.android.synthetic.main.activity_search_id.*
import kotlinx.android.synthetic.main.dialog_id_search.*

class SearchId : AppCompatActivity() {
    private val binding by lazy { ActivitySearchIdBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val editName = findViewById<EditText>(R.id.editName)
        val editBirth = findViewById<EditText>(R.id.editBirth)
        val searchIdBtn = findViewById<Button>(R.id.searchIdBtn)
        val resultText = findViewById<TextView>(R.id.resultText)

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

        // 액션바 설정
        setSupportActionBar(binding.toolbarIdSearch)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "아이디 찾기"
            // back button 커스텀
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_button)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbarIdSearch.setNavigationOnClickListener {
            onBackPressed()
        }

    }
}