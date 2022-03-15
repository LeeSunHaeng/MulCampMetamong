package com.example.bbssample

import android.os.Parcel
import android.os.Parcelable

class BbsDto(val seq:Int, val id:String?, val ref:Int, val step:Int, val depth:Int,
val title:String?, val content:String?, val wdate:String?, val del:Int, val readcount:Int) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(seq)
        parcel.writeString(id)
        parcel.writeInt(ref)
        parcel.writeInt(step)
        parcel.writeInt(depth)
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeString(wdate)
        parcel.writeInt(del)
        parcel.writeInt(readcount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BbsDto> {
        override fun createFromParcel(parcel: Parcel): BbsDto {
            return BbsDto(parcel)
        }

        override fun newArray(size: Int): Array<BbsDto?> {
            return arrayOfNulls(size)
        }
    }

}