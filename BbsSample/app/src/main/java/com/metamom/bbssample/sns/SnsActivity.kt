package com.metamom.bbssample.sns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.metamom.bbssample.R

class SnsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sns)
        val snsInsertBtn = findViewById<Button>(R.id.snsInsertBtn)
        val snsRecyclerView = findViewById<RecyclerView>(R.id.snsRecyclerView)




        //임시 데이터
        var data = SnsDao.getInstance().allSns()

       /* data.add(SnsDto(0,"doselage","김태리","profile3","2022-03-21","content://media/external/images/media/87",
            10,5,"삼겹살 너무 맛있다.."))
        data.add(SnsDto(1,"adrfs164","남주혁","profile3","2022-03-21","content://media/external/images/media/86",
            20,7,"치킨이 최고지.."))*/

        var adapter = CustomAdapter(this,data)
        snsRecyclerView.adapter = adapter

        val layout = LinearLayoutManager(this)
        snsRecyclerView.layoutManager = layout
        snsRecyclerView.setHasFixedSize(true)

        snsInsertBtn.setOnClickListener {
            val i  = Intent(this,SnsInsertActivity::class.java)
            startActivity(i)
        }

    }
}