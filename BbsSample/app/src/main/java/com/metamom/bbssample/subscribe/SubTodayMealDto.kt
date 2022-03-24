package com.metamom.bbssample.subscribe

import android.os.Parcel
import android.os.Parcelable

/* #21# 구독 _오늘의 식단 RecyclerView에 드로잉하기 위하여 파일 사용 */
class SubTodayMealDto(val subdfSeq:Int, val subdfTime:Int, val subdfImage:String?, val subdfName:String?, val subdfKcal:Int, val subdfAmount:Int) :Parcelable {

    // parcel에 대한 기본생성자
    constructor(parcel: Parcel) :this(
        parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt()
    ){ }

    override fun describeContents(): Int {
        return 0
    }

    // 실제 object를 serializtion/flattening하는 메소드
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(subdfSeq)
        parcel.writeInt(subdfTime)
        parcel.writeString(subdfImage)
        parcel.writeString(subdfName)
        parcel.writeInt(subdfKcal)
        parcel.writeInt(subdfAmount)
    }

    companion object CREATOR : Parcelable.Creator<SubTodayMealDto> {
        override fun createFromParcel(parcel: Parcel): SubTodayMealDto {
            return SubTodayMealDto(parcel)
        }

        override fun newArray(size: Int): Array<SubTodayMealDto?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "SubTodayMealDto(subdfSeq=${subdfSeq}, subdfTime=${subdfTime}, subdfImage=${subdfImage}, subdfName=${subdfName}, subdfKcal=${subdfKcal}, subdfAmount=${subdfAmount}"
    }
}