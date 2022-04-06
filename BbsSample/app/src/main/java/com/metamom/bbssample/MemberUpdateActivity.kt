package com.metamom.bbssample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.metamom.bbssample.fragments.HomeFragment
import com.metamom.bbssample.sns.SnsDao
import com.metamom.bbssample.subsingleton.MemberSingleton
import kotlinx.android.synthetic.main.activity_insert.*

/* #21# 회원정보 수정 */
class MemberUpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_update)

        val id = findViewById<EditText>(R.id.memUpdate_idEditTxt)
        val nickname = findViewById<EditText>(R.id.memUpdate_nicknameEdit)
        val name = findViewById<EditText>(R.id.memUpdate_nameEdit)
        val email = findViewById<EditText>(R.id.memUpdate_emailEdit)
        val phone = findViewById<EditText>(R.id.memUpdate_phoneEdit)
        val height = findViewById<EditText>(R.id.memUpdate_heightEdit)
        val weight = findViewById<EditText>(R.id.memUpdate_weightEdit)
        val birth = findViewById<EditText>(R.id.memUpdate_birthEdit)
        val genderGroup = findViewById<RadioGroup>(R.id.memUpdate_genderRadioGroup)
        val updateBtn = findViewById<Button>(R.id.memUpdate_updateBtn)

        /* 현재 로그인한 사용자의 회원정보 출력 */
        val userInfo = SnsDao.getInstance().snsGetMember(MemberSingleton.id.toString())
        Log.d("MemberUpdateActivity", "#21# 마이페이지 회원수정을 위하여 가져온 값 > $userInfo")

        if (userInfo != null){
            id.setText(userInfo.id)
            nickname.setText(userInfo.nickname)
            name.setText(userInfo.name)
            email.setText(userInfo.email)
            phone.setText(userInfo.phone)
            height.setText(userInfo.height.toString())
            weight.setText(userInfo.weight.toString())
            birth.setText(userInfo.birth)
        }
        else {
            Toast.makeText(this, "관리자에게 문의하여주시길 바립니다. 죄송합니다", Toast.LENGTH_LONG).show()
            Log.d("MemberUpdateActivity", "#21# MemberUpdateActivity 마이페이지 회원수정 페이지 > 회원정보 가져오기 실패 Error")
        }

        /* 회원정보 수정 */
        updateBtn.setOnClickListener {

            var isGoToUpdate = true

            // 1) 입력한 회원정보 각각의 변수에 저장
            val nickname = nickname.text.toString()
            val name = name.text.toString()
            val email = email.text.toString()
            val phone = phone.text.toString()
            val height = height.text.toString().toDouble()
            val weight = weight.text.toString().toDouble()
            val birth = birth.text.toString()

            // 남/여 Radio 버튼 값 불러오기
            var genderRadioBtn = findViewById<RadioButton>(genderGroup.checkedRadioButtonId)
            var gender :String? = genderRadioBtn.text.toString()

            if (gender == "남") gender = "M"
            else if (gender == "여") gender = "W"
            else gender = null

            // 2) 값이 비어있는지 확인 (무결성 검토)
            if (nickname.isNullOrEmpty()) {
                Toast.makeText(this, "닉네임을 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToUpdate = false
            }
            if (name.isNullOrEmpty()) {
                Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToUpdate = false
            }
            if (email.isNullOrEmpty()) {
                Toast.makeText(this, "이메일를 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToUpdate = false
            }
            if (phone.isNullOrEmpty()) {
                Toast.makeText(this, "전화번호를 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToUpdate = false
            }
            if (height.isNaN()) {
                Toast.makeText(this, "키를 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToUpdate = false
            }
            if (weight.isNaN()) {
                Toast.makeText(this, "몸무게를 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToUpdate = false
            }
            if (birth.isNullOrEmpty()) {
                Toast.makeText(this, "생년월일을 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToUpdate = false
            }
            if (gender.isNullOrEmpty()) {
                Toast.makeText(this, "성별을 선택해주세요", Toast.LENGTH_LONG).show()
                isGoToUpdate = false
            }


            // 3) 회원정보 수정
            if (isGoToUpdate) {
                val userUpdate = MemberDao.getInstance().userUpdate(MemberDto(id.text.toString(), "", name, email, gender, phone, nickname, height, weight, "", 0, 0, birth, 0.0, ""))
                Log.d("MemberUpdateActivity", "#21# MemberUpdateActivity _Back으로 부터 전달받은 회원정보 수정 결과값 > $userUpdate")

                if (userUpdate == true) {
                    Toast.makeText(this, "회원정보가 수정되었습니다!", Toast.LENGTH_LONG).show()

                    val i = Intent(this, MainButtonActivity::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(i)
                }
                else {
                    Toast.makeText(this, "관리자에게 문의하여 주시길 바랍니다. 죄송합니다", Toast.LENGTH_LONG).show()
                    Log.d("MemberUpdateActivity", "#21# MemberUpdateActivity _회원수정 실패 Error")
                }
            }
        }
    }
}