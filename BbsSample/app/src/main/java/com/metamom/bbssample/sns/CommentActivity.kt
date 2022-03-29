package com.metamom.bbssample.sns

import android.graphics.Rect
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
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
        val member = SnsDao.getInstance().snsGetMember(MemberSingleton.id!!)
        val uri = member.profile
        val cmtInsertContentEditText = findViewById<EditText>(R.id.cmtInsertContenteditText)
        val cmtInsertBtn = findViewById<ImageButton>(R.id.cmtInsertimageButton)

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
        var data = SnsDao.getInstance().allComment(seqData)

        //그걸 어댑터에 연결해서 리사이클러뷰에 뿌려줌
        var adapter = CommentAdapter(this,data)
        cmtRecyclerView.adapter = adapter

        val layout = LinearLayoutManager(this)
        cmtRecyclerView.layoutManager = layout
        cmtRecyclerView.setHasFixedSize(true)

        cmtInsertBtn.setOnClickListener {
            val dto = SnsCommentDto(seqData,MemberSingleton.id!!,
                member.nickname!!,member.profile!!,"방금",cmtInsertContentEditText.text.toString())
            SnsDao.getInstance().snsCommentInsert(dto)
            data = SnsDao.getInstance().allComment(seqData)
            adapter.addComment(dto)
            cmtInsertContentEditText.text = null

        }

    }
    //EditText 이외의 영역을 터치하면 키보드가 내려가게 하는 함수
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val focusView = currentFocus
        if (focusView != null && ev != null) {
            val rect = Rect()
            focusView.getGlobalVisibleRect(rect)
            val x = ev.x.toInt()
            val y = ev.y.toInt()

            if (!rect.contains(x, y)) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.hideSoftInputFromWindow(focusView.windowToken, 0)
                focusView.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}