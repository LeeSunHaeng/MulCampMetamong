package com.metamom.bbssample.subscribe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.metamom.bbssample.R

/* #21# 구독 _오늘의 식단 RecyclerView에 드로잉하기 위하여 파일 사용 */
class SubCustomAdapter(val context: Context, val dataDto: SubTodayMealDto) :RecyclerView.Adapter<SubItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.sub_view_item_meal, parent, false)
        return SubItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubItemViewHolder, position: Int) {
       holder.bind(dataDto, context)
    }

    override fun getItemCount(): Int {
        //return dataDto.size
        return 1
    }
}


class SubItemViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
    private val foodImage = itemView.findViewById<ImageView>(R.id.subItem_imageView)
    private val foodName = itemView.findViewById<TextView>(R.id.subItem_foodNameTxt)
    private val foodKcal = itemView.findViewById<TextView>(R.id.subItem_kcalTxt)

    // #21# sub_view_item_meal 레이아웃에 값 넣기(bind)
    fun bind(subDataVo:SubTodayMealDto, context: Context){

        /* #21# imageView에 url로 넣기 > build.gradle 내 code 추가 (Glide 사용)
        *  > Activity 사용 시와 ViewHolder에서 사용 시 code가 다르다. (ex. Acitivity 사용 시 Glide.with(this).load(url값).into(붙일 imageview) */
        var imageUrl:String = subDataVo.subdfImage.toString()
        Glide.with(itemView).load(imageUrl).into(foodImage)

        foodName.text = subDataVo.subdfName
        foodKcal.text = subDataVo.subdfKcal.toString()
    }
}