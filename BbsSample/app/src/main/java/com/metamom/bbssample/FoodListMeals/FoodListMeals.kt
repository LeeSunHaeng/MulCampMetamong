package com.metamom.bbssample.FoodListMeals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.metamom.bbssample.MemberSingleton
import com.metamom.bbssample.R
import com.metamom.bbssample.subscribe.SubscribeDao
import java.util.*

//나의 식단 기록 아침/점심/저녁
class FoodListMeals : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_list_meals)

        var cal = Calendar.getInstance()
        val addMealsBtn = findViewById<ImageButton>(R.id.addMealsBtn)
        var recyclerView = findViewById<RecyclerView>(R.id.FoodMealsList)
        var id = SubscribeDao.getInstance().getSubInfo(MemberSingleton.id.toString())

            val FoodList = FoodListMealsDao.getInstance().FoodListSelect()
            Log.d("어떻게나오나","${FoodList[0].foodscore}")

            val foodAdapter = FoodListAdapter(this,FoodList)
            recyclerView.adapter = foodAdapter


            val layout = LinearLayoutManager(this)
            recyclerView.layoutManager = layout
            recyclerView.setHasFixedSize(true)



        addMealsBtn.setOnClickListener {
            val i = Intent(this, AddFoodList::class.java)
            startActivity(i)
        }



    }
}