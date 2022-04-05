package com.metamom.bbssample.sns

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.metamom.bbssample.R

class SnsActivity : AppCompatActivity() {
    fun context():Context{
        return this
    }
    var data = SnsDao.getInstance().allSns()
    var adapter = CustomAdapter(this,data,supportFragmentManager)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sns)
        val snsInsertBtn = findViewById<Button>(R.id.snsInsertBtn)
        val snsRecyclerView = findViewById<RecyclerView>(R.id.snsRecyclerView)


        snsRecyclerView.adapter = adapter

        val layout = LinearLayoutManager(this)
        snsRecyclerView.layoutManager = layout
        snsRecyclerView.setHasFixedSize(true)

        snsInsertBtn.setOnClickListener {
            val i  = Intent(this,SnsInsertActivity::class.java)
            startActivity(i)
        }
        adapter


       /*if(intent.getSerializableExtra("position") as Int? != null){
           adapter.update(intent?.getSerializableExtra("position") as Int)
       }*/





    }



/*    override fun onResume() {
        super.onResume()
        println("resume~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~${intent.getSerializableExtra("position") as Int?}~~~~~~~~~~~~~~~~~~~~~~~~~")
        if(intent.getSerializableExtra("position") as Int? != null){
            adapter.update(intent?.getSerializableExtra("position") as Int)
        }
    }

    override fun onStart() {
        super.onStart()
        println("start~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~${intent.getSerializableExtra("position") as Int?}~~~~~~~~~~~~~~~~~~~~~~~~~")
        if(intent.getSerializableExtra("position") as Int? != null){
            adapter.update(intent?.getSerializableExtra("position") as Int)
        }
    }*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                100->{
                    val position = data?.getSerializableExtra("position") as Int
                    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$position~~~~~~~~~~~~~~~~~~~~~~~~~")
                    adapter.update(position)
                }
            }
        }
    }
}