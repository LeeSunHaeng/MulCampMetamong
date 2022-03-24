package com.metamom.bbssample.sns



import android.content.Context

import android.net.Uri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.metamom.bbssample.R


class CustomAdapter(val context: Context, val snsList:ArrayList<SnsDto>) : RecyclerView.Adapter<CustomAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        val snsProfile = itemView.findViewById<ImageView>(R.id.profileImageView)
        val snsNickName = itemView.findViewById<TextView>(R.id.nickNameTextView)
        val snsDate = itemView.findViewById<TextView>(R.id.dateTextView)
        val snsImageContent = itemView.findViewById<ImageView>(R.id.contentImageView)
        val snsLikeCount = itemView.findViewById<TextView>(R.id.likeCountTextView)
        val snsCommentCount = itemView.findViewById<TextView>(R.id.commentCountTextView)
        val snsContent = itemView.findViewById<TextView>(R.id.contentTextView)


        fun bind(dataVo:SnsDto, context: Context){

            if(dataVo.profile != ""){
                if(dataVo.profile.equals("profile3")){
                val resourceId = context.resources.getIdentifier(dataVo.profile, "drawable", context.packageName)

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

            snsNickName.text = dataVo.nickname
            snsDate.text = dataVo.snsdate
            snsLikeCount.text = dataVo.likecount.toString()
            snsCommentCount.text = dataVo.commentcount.toString()
            snsContent.text = dataVo.content

        }
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
