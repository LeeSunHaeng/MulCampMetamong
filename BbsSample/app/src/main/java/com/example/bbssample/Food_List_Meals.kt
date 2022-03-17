package com.example.bbssample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import java.util.*

//나의 식단 기록 아침/점심/저녁
class Food_List_Meals : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_list_meals)

        var cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH) //0~11월
        val dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)
        val addMealsBtn = findViewById<ImageButton>(R.id.addMealsBtn)

        addMealsBtn.setOnClickListener {
            val i = Intent(this,addFoodList::class.java)
            startActivity(i)
        }



    }
}