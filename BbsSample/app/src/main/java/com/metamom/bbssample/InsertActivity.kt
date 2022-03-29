package com.metamom.bbssample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.metamom.bbssample.databinding.ActivityInsertBinding

class InsertActivity : AppCompatActivity() {

    lateinit var id: EditText
    lateinit var pwd: EditText
    lateinit var pwd2: EditText
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
        pwd2 = findViewById(R.id.pwdInputCheck)
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

            var isGoToJoin = true

            val userId = id.text.toString()
            val userPwd = pwd.text.toString()
            val userPwd2 = pwd2.text.toString()
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

            // 값이 비어있는지 확인
            if (userId.isNullOrEmpty()) {
                Toast.makeText(this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }
            if (userPwd.isNullOrEmpty()) {
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }
            if (userPwd2.isNullOrEmpty()) {
                Toast.makeText(this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }
            if (userEmail.isNullOrEmpty()) {
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }
            if (userName.isNullOrEmpty()) {
                Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }
            if (userNickName.isNullOrEmpty()) {
                Toast.makeText(this, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }
            if (userPhone.isNullOrEmpty()) {
                Toast.makeText(this, "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }
            if (userHeight.isNaN()) {
                Toast.makeText(this, "키를 입력해주세요", Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }
            if (userWeight.isNaN()) {
                Toast.makeText(this, "몸무게를 입력해주세요", Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }
            if (userBirth.isNullOrEmpty()) {
                Toast.makeText(this, "생년월일을 입력해주세요", Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }
            // 비밀번호 2개가 같은지 확인
            if (userPwd != userPwd2) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }
            // 비밀번호가 6자 이상인지
            if (userPwd.length < 6) {
                Toast.makeText(this, "비밀번호를 6자리 이상으로 입력해주세요", Toast.LENGTH_SHORT).show()
                isGoToJoin = false
            }

            if (isGoToJoin) {
                MemberDao.getInstance().addmember(
                    MemberDto(
                        userId, userPwd, userName,
                        userEmail, userGender, userPhone, userNickName,
                        userHeight, userWeight, "n", 0, 0,
                        userBirth, 0.0, ""
                    )
                )


                Toast.makeText(this, "가입되었습니다", Toast.LENGTH_LONG).show()

                val i = Intent(this, MainActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
            }
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
