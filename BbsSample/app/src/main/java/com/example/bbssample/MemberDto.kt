package com.example.bbssample

import android.os.Parcel
import android.os.Parcelable

class MemberDto(val id: String?, val pwd: String?, val name: String?, val email: String?, val auth: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(pwd)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeInt(auth)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MemberDto> {
        override fun createFromParcel(parcel: Parcel): MemberDto {
            return MemberDto(parcel)
        }

        override fun newArray(size: Int): Array<MemberDto?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "MemberDto(id=$id, pwd=$pwd, name=$name, email=$email, auth=$auth)"
    }
}
