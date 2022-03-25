package com.metamom.bbssample. subscribe

import android.os.Parcel
import android.os.Parcelable

/* #21# 구독 _오늘의 식단 [운동] RecyclerView에 드로잉하기 위하여 파일 사용 */
class SubExerMealDto(val subefSeq:Int, val subefTime:Int, val subefImage:String?, val subefName:String?, val subefKcal:Int, val subefAmount:Int, val subefType:String?) : Parcelable {

    // parcel에 대한 기본생성자
    constructor(parcel: Parcel) :this(
        parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString()
    ){ }

    override fun describeContents(): Int {
        return 0
    }

    // 실제 object를 serializtion/flattening하는 메소드
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(subefSeq)
        parcel.writeInt(subefTime)
        parcel.writeString(subefImage)
        parcel.writeString(subefName)
        parcel.writeInt(subefKcal)
        parcel.writeInt(subefAmount)
        parcel.writeString(subefType)
    }

    companion object CREATOR : Parcelable.Creator<SubExerMealDto> {
        override fun createFromParcel(parcel: Parcel): SubExerMealDto {
            return SubExerMealDto(parcel)
        }

        override fun newArray(size: Int): Array<SubExerMealDto?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "SubExerMealDto(subefSeq=${subefSeq}, subefTime=${subefTime}, subefImage=${subefImage}, subefName=${subefName}, subefKcal=${subefKcal}, subefAmount=${subefAmount}, subefType=${subefType}"
    }

}