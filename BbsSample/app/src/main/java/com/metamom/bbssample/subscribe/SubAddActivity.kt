package com.metamom.bbssample.subscribe

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.metamom.bbssample.MainButtonActivity
import com.metamom.bbssample.R
import com.metamom.bbssample.subsingleto.MemberSingleton
import com.metamom.bbssample.subsingleton.SubAddSingleton

class SubAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_add)

        /* #21# 뒤로가기(이전화면) */
        val previousBtn = findViewById<ImageButton>(R.id.subAdd_PreBtn)
        previousBtn.setOnClickListener {
            val i = Intent(this, MainButtonActivity::class.java)
            startActivity(i)
        }

        /* #21# Spinner 세팅 + Spinner 선택 값 SubAddSingleton에 저장 */
        setupSpinner()                                  // Spinner 세팅
        setupSpinnerHandler()                           // Spinner 선택 시 이벤트

        /* #21# 구독신청 (+ 멤버 DB 내 구독값 수정) */
        val subAddBtn = findViewById<Button>(R.id.subAdd_addBtn)
        subAddBtn.setOnClickListener {

            timeCheck()     // 구독 시간 선택함수 호출

            if (SubAddSingleton.subType != null && SubAddSingleton.subType != null){
                val addResult = SubscribeDao.getInstance().subAdd(SubscribeDto(MemberSingleton.id.toString(),
                                                                                SubAddSingleton.subType!!,
                                                                                SubAddSingleton.subPeriod!!,
                                                                                SubAddSingleton.subMorning,
                                                                                SubAddSingleton.subLunch,
                                                                                SubAddSingleton.subDinner,
                                                                                SubAddSingleton.subSnack,
                                                                                "", ""))
                Log.d("SubAddActivity", "#21# 구독 신청 Back으로부터 전달받은 결과값 > ${addResult.toString()}")

                if (addResult == "Success"){
                    val builder = AlertDialog.Builder(this);
                    builder.setTitle("구독 신청")
                    builder.setMessage("구독이 신청되었습니다! 감사합니다 😌")
                    builder.show()
                } else {
                    Toast.makeText(this, "죄송합니다. 다시 시도해주세요", Toast.LENGTH_LONG).show()
                }
            }
        }
    }




    /* Spinner 세팅 */
    fun setupSpinner() {

        // 1) 유형 Spinner
        val typeData = resources.getStringArray(R.array.subtype)                                    // subadd_array.xml 내 subtype 배열
        val typeAdapter = ArrayAdapter(this, R.layout.subadd_item_spinner, typeData)        // typeAdapter 변수에 배열과 xml을 적용

        val TypeSpinner = findViewById<Spinner>(R.id.subAdd_TypeSpinn)
        TypeSpinner.adapter = typeAdapter

        // 2) 기간 Spinner
        val periodData = resources.getStringArray(R.array.subperiod)
        val periodAdapter = ArrayAdapter(this, R.layout.subadd_item_spinner, periodData)

        val periodSpinner = findViewById<Spinner>(R.id.subAdd_PeriodSpinn)
        periodSpinner.adapter = periodAdapter
    }


    /* Spinner 선택 시 이벤트 */
    fun setupSpinnerHandler() {

        // 1) 유형 Spinner
        val TypeSpinner = findViewById<Spinner>(R.id.subAdd_TypeSpinn)
        TypeSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            // 선택되었을 때
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("SubAddActivity", "#21# Type Spinner 선택한 값: ${TypeSpinner.getItemAtPosition(position)}")      // getItemAtPosition == position에 해당되는 item 가져오기

                if (TypeSpinner.getItemAtPosition(position).toString() == "다이어트") {
                    SubAddSingleton.subType = 0
                } else if (TypeSpinner.getItemAtPosition(position).toString() == "운동") {
                    SubAddSingleton.subType = 1
                } else if (TypeSpinner.getItemAtPosition(position).toString() == "건강") {
                    SubAddSingleton.subType = 2
                } else {
                    Log.d("SubAddActivity", "#21# 구독 유형 Spinner 선택 Error")
                }
            }

            // 아무것도 선택되지 않았을 때
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        // 2) 기간 Spinner
        val PeriodSpinner = findViewById<Spinner>(R.id.subAdd_PeriodSpinn)
        PeriodSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            // 선택되었을 때
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("SubAddActivity", "#21# Period Spinner 선택한 값: ${PeriodSpinner.getItemAtPosition(position)}")

                if (PeriodSpinner.getItemAtPosition(position).toString() == "1개월[1,900원]") {
                    SubAddSingleton.subPeriod = 1
                } else if (PeriodSpinner.getItemAtPosition(position).toString() == "3개월[3,900원]") {
                    SubAddSingleton.subPeriod = 3
                } else if (PeriodSpinner.getItemAtPosition(position).toString() == "5개월[5,900원]") {
                    SubAddSingleton.subPeriod = 5
                } else {
                    Log.d("SubAddActivity", "#21# 구독 기간 Spinner 선택 Error")
                }
            }

            // 아무것도 선택되지 않았을 때
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    /* 3) 구독 시간 선택값 Singleton에 저장  */
    fun timeCheck() {
        val morningCheckBox = findViewById<CheckBox>(R.id.subAdd_morningCheck)
        val lunchCheckBox = findViewById<CheckBox>(R.id.subAdd_lunchCheck)
        val dinnerCheckBox = findViewById<CheckBox>(R.id.subAdd_dinnerCheck)
        val snackCheckBox = findViewById<CheckBox>(R.id.subAdd_snackCheck)
        if(morningCheckBox.isChecked) {
            SubAddSingleton.subMorning = 1
        } else {
            SubAddSingleton.subMorning = 0
        }
        if (lunchCheckBox.isChecked) {
            SubAddSingleton.subLunch = 1
        } else {
            SubAddSingleton.subLunch = 0
        }
        if (dinnerCheckBox.isChecked) {
            SubAddSingleton.subDinner = 1
        } else {
            SubAddSingleton.subDinner = 0
        }
        if (snackCheckBox.isChecked) {
            SubAddSingleton.subSnack = 1
        } else {
            SubAddSingleton.subSnack = 0
        }
    }
}