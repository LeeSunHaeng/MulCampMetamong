package com.example.bbssample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bbssample.databinding.ActivityInsertBinding

class InsertActivity : AppCompatActivity() {

    private val binding by lazy { ActivityInsertBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 액션바 설정
        setSupportActionBar(binding.toolbarInsertMember)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "회원가입"
        }
        binding.toolbarInsertMember.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}