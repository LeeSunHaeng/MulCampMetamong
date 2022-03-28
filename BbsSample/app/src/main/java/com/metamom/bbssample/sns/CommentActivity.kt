package com.metamom.bbssample.sns

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.metamom.bbssample.R
import com.metamom.bbssample.subsingleton.MemberSingleton

class CommentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        val cmtRecyclerView = findViewById<RecyclerView>(R.id.cmtRecyclerView)
        val cmtInsertProfile = findViewById<ImageView>(R.id.cmtInsertProfileImageView)
        val uri = SnsDao.getInstance().snsGetMember(MemberSingleton.id!!).profile

        if(uri != ""){
            if(uri.equals("profile3")){
                val resourceId = this.resources.getIdentifier(uri, "drawable", this.packageName)
                println("~~~~~~~~~resourceId : ${resourceId}")
                if(resourceId > 0){
                    cmtInsertProfile.setImageResource(resourceId)
                }else{
                    Glide.with(this).load(uri).into(cmtInsertProfile)
                }
            }
        } else{
            val profileUri: Uri = Uri.parse(uri)
            cmtInsertProfile.setImageURI(profileUri)


        }


        //게시물의 seq 값을 전달 받음
        val seqData = intent.getSerializableExtra("seq") as Int

        //Comment DB에 전달받은 seq로 저장되어있는 데이터들을 불러옴,
        val data = SnsDao.getInstance().allComment(seqData)

        //그걸 어댑터에 연결해서 리사이클러뷰에 뿌려줌
        var adapter = CommentAdapter(this,data)
        cmtRecyclerView.adapter = adapter

        val layout = LinearLayoutManager(this)
        cmtRecyclerView.layoutManager = layout
        cmtRecyclerView.setHasFixedSize(true)
    }
}