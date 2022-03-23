package com.metamom.bbssample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.metamom.bbssample.databinding.ActivityInsertBinding

class InsertActivity : AppCompatActivity() {

    lateinit var id: EditText
    lateinit var pwd: EditText
    lateinit var name: EditText
    lateinit var nickName: EditText
    lateinit var email: EditText
    lateinit var phone: EditText
    lateinit var height: EditText
    lateinit var weight: EditText
    lateinit var birth: EditText
    lateinit var gender: RadioGroup
    lateinit var registerBtn: Button

    private val binding by lazy { ActivityInsertBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        id = findViewById(R.id.idInput)
        pwd = findViewById(R.id.pwdInput)
        name = findViewById(R.id.nameInput)
        nickName = findViewById(R.id.nicknameInput)
        email = findViewById(R.id.emailInput)
        phone = findViewById(R.id.phoneNumberInput)
        height = findViewById(R.id.heightInput)
        weight = findViewById(R.id.weightInput)
        birth = findViewById(R.id.birthInput)
        gender = findViewById(R.id.genderRadio)
        registerBtn = findViewById(R.id.registerBtn)

        registerBtn.setOnClickListener {

            val userId = id.text.toString()
            val userPwd = pwd.text.toString()
            val userName = name.text.toString()
            val userNickName = nickName.text.toString()
            val userEmail = email.text.toString()
            val userPhone = phone.text.toString()
            val userHeight = height.text.toString().toDouble()
            val userWeight = weight.text.toString().toDouble()
            val userBirth = birth.text.toString()

            // 라디오 버튼인 gender의 값 불러오기
            var userGenderBtn = findViewById<RadioButton>(gender.checkedRadioButtonId)
            val userGender = userGenderBtn.text.toString()

            MemberDao.getInstance().addmember(
                MemberDto(userId, userPwd, userName,
                    userEmail, userGender, userPhone, userNickName,
                    userHeight, userWeight, "n", 0, 0,
                    userBirth, 0.0, "")
            )

            Toast.makeText(this, "가입되었습니다", Toast.LENGTH_LONG).show()

            val i = Intent(this, MainButtonActivity::class.java)
            startActivity(i)
        }

        // 액션바 설정
        setSupportActionBar(binding.toolbarInsertMember)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = ""
            // back button 커스텀
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_button);
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbarInsertMember.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}
