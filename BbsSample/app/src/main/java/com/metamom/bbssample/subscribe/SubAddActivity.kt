package com.metamom.bbssample.subscribe

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.metamom.bbssample.MainButtonActivity
import com.metamom.bbssample.R
import com.metamom.bbssample.databinding.ActivitySubAddBinding
import com.metamom.bbssample.subsingleton.MemberSingleton
import com.metamom.bbssample.subsingleton.SubAddSingleton
import com.metamom.bbssample.subsingleton.SubPurchaseSingleton

class SubAddActivity : AppCompatActivity() {

    /* #21# 구글 인앱 결제 사용을 위하여 binding 사용 */
    private lateinit var binding: ActivitySubAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* #21# 구글 인앱 결제 사용을 위하여 binding 사용 */
        binding = ActivitySubAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_sub_add)

        /* 액션바 설정 */
        setSupportActionBar(findViewById(R.id.subAdd_toolbar))
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = ""
            // back button 커스텀
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_button)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        findViewById<Toolbar>(R.id.subAdd_toolbar).setNavigationOnClickListener {
            onBackPressed()
        }

        /* #21# Spinner 세팅 + Spinner 선택 값 SubAddSingleton에 저장 */
        setupSpinner()                                  // Spinner 세팅
        setupSpinnerHandler()                           // Spinner 선택 시 이벤트

        /* #21# 구독신청 (+ 멤버 TABLE 내 구독값 수정) */
        val subAddBtn = findViewById<Button>(R.id.subAdd_addBtn)

        with (binding) {
            subAddBtn.setOnClickListener {

                // 선택한 개월(1, 3, 5) Singleton에 저장
                SubPurchaseSingleton.subPeriod = SubAddSingleton.subPeriod

                // 선택한 구독 시간 Singleton에 저장
                timeCheck()

                /* !! 구글 인앱 결제를 위한 Activity로 이동 */
                val i = Intent(this@SubAddActivity, SubPurchaseActivity::class.java)
                startActivity(i)

                // #21# (04.05) 아래의 code는 결제이후 진행해야 하기 때문에 SubPurchaseActivity 로 이동
                /*
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

                        *//* !!! 구독 신청 후 MemberSingleton값 수정 *//*
                        MemberSingleton.subscribe = "1"
                    } else {
                        Toast.makeText(this@SubAddActivity, "죄송합니다. 다시 시도해주세요", Toast.LENGTH_LONG).show()
                    }
                }*/
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

                if (PeriodSpinner.getItemAtPosition(position).toString() == "1개월[1,000원]") {
                    SubAddSingleton.subPeriod = 1
                } else if (PeriodSpinner.getItemAtPosition(position).toString() == "3개월[3,000원]") {
                    SubAddSingleton.subPeriod = 3
                } else if (PeriodSpinner.getItemAtPosition(position).toString() == "5개월[5,000원]") {
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