package com.metamom.bbssample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.metamom.bbssample.databinding.ActivityInsertBinding

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
            // back button 커스텀
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_button);
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbarInsertMember.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}