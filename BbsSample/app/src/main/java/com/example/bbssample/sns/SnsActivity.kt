package com.example.bbssample.sns

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbssample.R

class SnsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sns)

        val snsRecyclerView = findViewById<RecyclerView>(R.id.snsRecyclerView)
        var data:MutableList<SnsDto> = mutableListOf()
        data.add(SnsDto(0,"doselage","profile1","2022-03-21","food1",
            10,5,"삼겹살 너무 맛있다.."))
        data.add(SnsDto(1,"adrfs164","profile2","2022-03-21","food2",
            20,7,"치킨이 최고지.."))

        var adapter = CustomAdapter(this,data)
        snsRecyclerView.adapter = adapter

        val layout = LinearLayoutManager(this)
        snsRecyclerView.layoutManager = layout
        snsRecyclerView.setHasFixedSize(true)

    }
}