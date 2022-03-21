package com.metamom.bbssample.FoodListMeals

import android.os.Parcel
import android.os.Parcelable

class FoodListMealsDto(val id:String?,val wdate:String?,val meals:String?,val memo:String?,val foodName:String?,val imgUrl:String?,val foodscore:String?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ){}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(wdate)
        parcel.writeString(meals)
        parcel.writeString(memo)
        parcel.writeString(foodName)
        parcel.writeString(imgUrl)
        parcel.writeString(foodscore)
    }
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FoodListMealsDto> {
        override fun createFromParcel(parcel: Parcel): FoodListMealsDto {
            return FoodListMealsDto(parcel)
        }

        override fun newArray(size: Int): Array<FoodListMealsDto?> {
            return arrayOfNulls(size)
        }
    }


}