package com.example.bbssample

import android.os.Parcel
import android.os.Parcelable

class FoodListMealsDto(val id:String?,val wdate:String?,val meals:String?,val memo:String?,val food_name:String?,val img_url:String?,val food_score:String?):Parcelable {
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
        parcel.writeString(food_name)
        parcel.writeString(img_url)
        parcel.writeString(food_score)
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