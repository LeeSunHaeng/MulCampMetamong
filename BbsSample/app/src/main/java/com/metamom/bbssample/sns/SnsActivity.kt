package com.metamom.bbssample.sns

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.metamom.bbssample.R
import kotlinx.android.synthetic.main.activity_sns.*
import kotlinx.android.synthetic.main.activity_sns_update.*

class SnsActivity : AppCompatActivity() {
    fun context():Context{
        return this
    }
    fun fragmentManager():FragmentManager{
        return supportFragmentManager
    }
    var data = SnsDao.getInstance().allSns()
    val adapter = CustomAdapter(this,data!!,supportFragmentManager)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sns)
        val snsRecyclerView = findViewById<RecyclerView>(R.id.snsRecyclerView)

        //툴바 사용 설정
        setSupportActionBar(snsMainToolbar)
        // 툴바 왼쪽 버튼 설정
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back_button)
        supportActionBar!!.title="META_SNS"


        snsRecyclerView.adapter = adapter

        val layout = LinearLayoutManager(this)
        snsRecyclerView.layoutManager = layout
        snsRecyclerView.setHasFixedSize(true)



    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                //댓글 갯수 카운트
                100->{
                    val position = data?.getSerializableExtra("position") as Int
                    adapter.diffUpdate(SnsDao.getInstance().allSns())
                    snsRecyclerView.scrollToPosition(position)
                }
                //게시물 수정
                200->{
                    val position = data?.getSerializableExtra("position") as Int

                    adapter.diffUpdate(SnsDao.getInstance().allSns())
                    snsRecyclerView.scrollToPosition(position)
                }
                //게시물 작성
                300 ->{
                    adapter.diffUpdate(SnsDao.getInstance().allSns())
                    snsRecyclerView.scrollToPosition(0)
                }

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sns_main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            android.R.id.home ->{
                finish()
            }

            R.id.snsMainInsertBtn ->{
                val i = Intent(this,SnsInsertActivity::class.java)
                 startActivityForResult(i,300)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}