package com.metamom.bbssample.sns

import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.metamom.bbssample.R
import com.metamom.bbssample.subsingleton.MemberSingleton
import kotlinx.android.synthetic.main.activity_comment.*

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
        //툴바 사용 설정
        setSupportActionBar(snstoolbar)
        // 툴바 왼쪽 버튼 설정
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back_button)
        supportActionBar!!.title="댓글"




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
            val dto = SnsCommentDto(0,seqData,MemberSingleton.id!!,
                member.nickname!!,member.profile!!,"방금",cmtInsertContentEditText.text.toString())
            SnsDao.getInstance().snsCommentInsert(dto)
            adapter.addComment(dto)
            cmtInsertContentEditText.text = null
            cmtRecyclerView.scrollToPosition(data.size-1)
        }



        //수정 해야함!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        /*cmtBackBtn.setOnClickListener {
            val data = SnsDao.getInstance().allSns()
            val adapter = CustomAdapter(SnsActivity().context(),data,supportFragmentManager)
            val snsCommentCountText = findViewById<TextView>(R.id.commentCountTextView)
            val seq =intent.getStringExtra("seq")
            snsCommentCountText.text = "댓글 ${SnsDao.getInstance().snsCommentCount(seq!!.toInt())}개"
            val i = Intent(this,SnsActivity::class.java)
            startActivity(i)



        }*/

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
    //툴바 메뉴 버튼 설정
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sns_menu,menu)
        return true
    }
    //툴바 메뉴 버튼이 클릭 됐을 때 콜백
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            //왼쪽 버튼이 클릭 됐을때 (뒤로가기 버튼)
            android.R.id.home ->{
                val data = intent.getSerializableExtra("pos") as Int
                val i = Intent()
                i.putExtra("position",data)
                setResult(Activity.RESULT_OK,i)
                finish()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
