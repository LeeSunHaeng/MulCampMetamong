package com.metamom.bbssample.sns

import com.metamom.bbssample.RetrofitClient
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface SnsService{
    @POST("/snsInsert")
    fun snsInsert(@Body dto:SnsDto): Call<Int?>
}

class SnsDao {
    companion object {
        var SnsDao: SnsDao? = null
        fun getInstance(): SnsDao {
            if (SnsDao == null) {
                SnsDao = SnsDao()
            }
            return SnsDao!!
        }
    }


    fun snsInsert(dto: SnsDto): Int? {
        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(SnsService::class.java)
        val call = service?.snsInsert(dto)
        val response = call?.execute()

        return response?.body()
    }
}
