package com.metamom.bbssample.FoodListMeals

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.metamom.bbssample.R


class FoodListAdapter(val context:Context,val writeFoodSelect:ArrayList<FoodListMealsDto>) : RecyclerView.Adapter<FoodListAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        val foodImg = itemView.findViewById<ImageView>(R.id.foodImg)
        val foodKindTxt = itemView.findViewById<TextView>(R.id.foodKindTxt)
        val timeTxt = itemView.findViewById<TextView>(R.id.timeTxt)
        val foodScoreTxt = itemView.findViewById<TextView>(R.id.foodScoreTxt)
        val memoTxt = itemView.findViewById<TextView>(R.id.memoTxt)

       fun bind(Vo:FoodListMealsDto,context: Context){

           //foodImg  사진/카메라로 찍은 사진
           foodKindTxt.text = Vo.meals //식사종류
           timeTxt.text = Vo.wdate //시간
           memoTxt.text = Vo.memo // 메모
           if (Vo.foodscore =="1점"){ //점수
               foodScoreTxt.text="점수 : ★"
           }else if(Vo.foodscore =="2점"){
               foodScoreTxt.text="점수 : ★★"
           }else if(Vo.foodscore =="3점"){
               foodScoreTxt.text="점수 : ★★★"
           }else if(Vo.foodscore =="4점"){
               foodScoreTxt.text="점수 : ★★★★"
           }else if(Vo.foodscore =="5점"){
               foodScoreTxt.text="점수 : ★★★★★"
           }else foodScoreTxt.text="점수 : ${Vo.foodscore}"
          // foodImg.setImageURI((Vo.imgUrl))
           try {
               if(Vo.imgUrl != null) {
                   val uri = Uri.parse("${Vo.imgUrl}")
                   foodImg.setImageURI(uri)
               }else{
                   foodImg.setImageResource(R.drawable.xbox)
               }
           } catch (e: Exception) {
               e.printStackTrace()
           }
       }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListAdapter.ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.meals_view_item_layout,parent,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodListAdapter.ItemViewHolder, position: Int) {
        holder.bind(writeFoodSelect[position],context)
    }

    override fun getItemCount(): Int {
        return writeFoodSelect.size
    }
}


