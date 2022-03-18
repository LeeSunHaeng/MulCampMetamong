package com.example.bbssample.subscribe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.bbssample.MainButtonActivity
import com.example.bbssample.MemberSingleton
import com.example.bbssample.R

class SubInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_info)

        val idTxt = findViewById<TextView>(R.id.subInfo_IdTxt)
        val typeTxt = findViewById<TextView>(R.id.subInfo_TypeTxt)
        val periodTxt = findViewById<TextView>(R.id.subInfo_PeriodTxt)
        val startdayTxt = findViewById<TextView>(R.id.subInfo_StartDayTxt)

        val morningCheck = findViewById<CheckBox>(R.id.subInfo_MorningChkBox)
        val lunchCheck = findViewById<CheckBox>(R.id.subInfo_LunchChkBox)
        val dinnerCheck = findViewById<CheckBox>(R.id.subInfo_DinnerChkBox)
        val snackCheck = findViewById<CheckBox>(R.id.subInfo_SnackChkBox)

        /* #21# 구독 회원정보 가져오기
        *  case_1) 회원정보 가져오기 실패 시 알림창 & main Button 페이지로 이동
        *  case_2) 회원정보 드로잉 */
        var subInfo = SubscribeDao.getInstance().getSubInfo(MemberSingleton.id.toString())

        if (subInfo != null){                                           // case_2)
            SubscribeDao.subUser = subInfo

            idTxt.text = subInfo.subId.toString()
            if (subInfo.subType.toString() == "0"){
                typeTxt.text = "다이어트"
            } else if (subInfo.subType.toString() == "1"){
                typeTxt.text = "운동"
            } else {
                typeTxt.text = "건강"
            }

            periodTxt.text = "${subInfo.subPeriod.toString()}개월"
            startdayTxt.text = subInfo.subStartDay.toString()

            // !! 체크박스 비활성화 필요
            if (subInfo.subMorning == 1){
                morningCheck.isChecked = true
            }
            if (subInfo.subLunch == 1){
                lunchCheck.isChecked = true
            }
            if (subInfo.subDinner == 1){
                lunchCheck.isChecked = true
            }
            if (subInfo.subSnack == 1){
                lunchCheck.isChecked = true
            }
        }
        else {                                                          // case_1)
            Toast.makeText(this, "죄송합니다. 다시 시도해주세요!", Toast.LENGTH_LONG).show()

            val i = Intent(this, MainButtonActivity::class.java)
            startActivity(i)
        }
    }
}