package com.example.bbssample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class BbsWriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bbs_write)

        val id = findViewById<TextView>(R.id.writerTxt)
        id.text = MemberDao.user?.id

        val title = findViewById<EditText>(R.id.editTitle)
        val content = findViewById<EditText>(R.id.editContent)

        val writeBtn = findViewById<Button>(R.id.writeBtn)

        writeBtn.setOnClickListener {

            println(title.text.toString())
            println(content.text.toString())

            val str = BbsDao.getInstance().bbswrite(BbsDto(0, MemberDao.user?.id, 0, 0, 0,
                                    title.text.toString(), content.text.toString(), "",
                                    0, 0))
           Toast.makeText(this, "추가되었습니다", Toast.LENGTH_LONG).show()

           val i = Intent(this, BbsListActivity::class.java)
           startActivity(i)
        }
    }
}