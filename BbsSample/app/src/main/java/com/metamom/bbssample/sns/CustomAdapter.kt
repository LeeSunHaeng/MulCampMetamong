package com.metamom.bbssample.sns



import android.content.Context
import android.content.Intent

import android.net.Uri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.metamom.bbssample.R
import com.metamom.bbssample.subsingleton.MemberSingleton


class CustomAdapter(val context: Context, val snsList:ArrayList<SnsDto>) : RecyclerView.Adapter<CustomAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        val snsProfile = itemView.findViewById<ImageView>(R.id.profileImageView)
        val snsNickName = itemView.findViewById<TextView>(R.id.nickNameTextView)
        val snsDate = itemView.findViewById<TextView>(R.id.dateTextView)
        val snsImageContent = itemView.findViewById<ImageView>(R.id.contentImageView)
        val snsLikeCount = itemView.findViewById<TextView>(R.id.likeCountTextView)
        val snsCommentCount = itemView.findViewById<TextView>(R.id.commentCountTextView)
        val snsContent = itemView.findViewById<TextView>(R.id.contentTextView)
        val likeBtn = itemView.findViewById<ImageButton>(R.id.likeImageButton)
        val snsCommentBtn = itemView.findViewById<ImageButton>(R.id.commentImageButton)

        fun bind(dataVo:SnsDto, context: Context){

            //프로필 이미지 뿌려주기
            if(dataVo.profile != ""){
                if(dataVo.profile.equals("profile3")){
                val resourceId = context.resources.getIdentifier(dataVo.profile, "drawable", context.packageName)
                println("~~~~~~~~~resourceId : ${resourceId}")
                if(resourceId > 0){
                    snsProfile.setImageResource(resourceId)
                }else{
                    Glide.with(itemView).load(dataVo.profile).into(snsProfile)
                }
                }
            } else{
                val profileUri:Uri = Uri.parse(dataVo.profile)
                snsProfile.setImageURI(profileUri)

                //snsProfile.setImageResource(R.mipmap.ic_launcher_round) // 이미지 없다. 아무 이미지나 뿌린다
            }

            /*if(dataVo.imageContent != ""){
                val resourceId = context.resources.getIdentifier(dataVo.imageContent, "drawable", context.packageName)

                if(resourceId > 0){
                    snsImageContent.setImageResource(resourceId)
                }else{
                    Glide.with(itemView).load(dataVo.imageContent).into(snsImageContent)
                }
            } else{
                snsImageContent.setImageResource(R.mipmap.ic_launcher_round) // 이미지 없다. 아무 이미지나 뿌린다
            }*/
            if(dataVo.imagecontent != ""){
                //val resourceId = context.resources.getIdentifier(dataVo.imageContent, "drawable", context.packageName)

                val snsUri:Uri = Uri.parse(dataVo.imagecontent)

                snsImageContent.setImageURI(snsUri)

            } else{
                snsImageContent.setImageResource(R.mipmap.ic_launcher_round) // 이미지 없다. 아무 이미지나 뿌린다
            }

            //게시물 올린 시간에 따라 다르게 뿌려줌
            val wdate = dataVo.snsdate!!.split("-")
            if(wdate.get(0).equals("0")){
                if(wdate.get(1).equals("0")) {
                    snsDate.text = "${wdate.get(2)}분"
                }else{
                    snsDate.text = "${wdate.get(1)}시간"
                }
            }else if(wdate.get(0).equals("1")){
                snsDate.text = "어제"
            }else{
                snsDate.text = "${wdate.get(0)}일"
            }

            snsNickName.text = dataVo.nickname
            snsLikeCount.text = SnsDao.getInstance().snsLikeCount(dataVo.seq).toString()
            snsCommentCount.text = dataVo.commentcount.toString()
            snsContent.text = dataVo.content

            //좋아요 버튼 이미지 뿌려줄때
            var snsLikeCheck = SnsDao.getInstance().snsLikeCheck(SnsLikeDto(dataVo.seq,MemberSingleton.id!!,"YY/MM/DD"))
            if(snsLikeCheck > 0){
                val resourceId = context.resources.getIdentifier("pinklike_icon", "drawable", context.packageName)
                likeBtn.setImageResource(resourceId)
            }else{
                val resourceId = context.resources.getIdentifier("like_icon", "drawable", context.packageName)
                likeBtn.setImageResource(resourceId)
            }
            //뿌리고 난 후 좋아요 버튼을 눌렀을때
            likeBtn.setOnClickListener {
                val dto = SnsLikeDto(dataVo.seq,MemberSingleton.id!!,"YY/MM/DD")
                snsLikeCheck = SnsDao.getInstance().snsLikeCheck(dto)
                //좋아요가 눌려 있을때
                if(snsLikeCheck > 0){
                    //이미지 변경
                    val resourceId = context.resources.getIdentifier("like_icon", "drawable", context.packageName)
                    likeBtn.setImageResource(resourceId)
                    SnsDao.getInstance().snsLikeDelete(dto)

                }
                //좋아요가 안눌려 있을때
                else{
                    val resourceId = context.resources.getIdentifier("pinklike_icon", "drawable", context.packageName)
                    likeBtn.setImageResource(resourceId)

                    SnsDao.getInstance().snsLikeInsert(dto)

                }

                snsLikeCount.text = SnsDao.getInstance().snsLikeCount(dataVo.seq).toString()

            }
            snsCommentBtn.setOnClickListener {
                Intent(context,CommentActivity::class.java).apply {
                    putExtra("seq",dataVo.seq)
                }.run { context.startActivity(this) }

            }


        }


        /*init {
            likeBtn.setOnClickListener {
                if()

            }
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.sns_view_item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(snsList[position], context)
    }

    override fun getItemCount(): Int {
        return snsList.size
    }


}
