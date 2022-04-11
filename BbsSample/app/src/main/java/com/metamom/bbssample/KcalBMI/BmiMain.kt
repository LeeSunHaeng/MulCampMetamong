package com.metamom.bbssample.KcalBMI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.metamom.bbssample.R
import java.text.DecimalFormat
import kotlin.math.roundToInt

class BmiMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_main)

        val BmiHeight = findViewById<EditText>(R.id.BmiHeight)
        val BmiWeight = findViewById<EditText>(R.id.BmiWeight)
        val BmiBtn = findViewById<Button>(R.id.BmiBtn)
        val BmiResult = findViewById<TextView>(R.id.BmiResult)

        BmiBtn.setOnClickListener{

            var height:String = BmiHeight.text.toString()
            var weight:String = BmiWeight.text.toString()

            val result:Double = weight.toDouble() / ( height.toDouble() * height.toDouble() ) * 10000

           // val result2 = (result*100).roundToInt() / 100

            val df = DecimalFormat("00.0")
            val result2 = df.format(result)

            //BmiResult.text = result2.toString()

            if(result < 18.5){
                BmiResult.text = "BMI : " + result2.toString() + "이며 저체중입니다"
            }else if(result < 22.9){
                BmiResult.text = "BMI : " + result2.toString()+ "이며 정상체중입니다"
            }else if(result < 24.9){
                BmiResult.text = "BMI : " + result2.toString() + "이며 과체중입니다"
            }else if(result < 35){
                BmiResult.text = "BMI : " + result2.toString() + "이며 비만입니다"
            }else {
                BmiResult.text = "BMI : " + result2.toString() + "이며 고도비만입니다"
            }
        }




    }
}