package com.metamom.bbssample.sns

import com.metamom.bbssample.MemberDto
import com.metamom.bbssample.RetrofitClient
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface SnsService{
    @POST("/snsInsert")
    fun snsInsert(@Body dto:SnsDto): Call<Int?>

    @POST("/snsGetMember")
    fun snsGetMember(@Query("id") id:String):Call<MemberDto>

    @GET("allSns")
    fun allSns() : Call<List<SnsDto>>
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

    fun snsGetMember(id:String):MemberDto{
        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(SnsService::class.java)
        val call = service?.snsGetMember(id)
        val response = call?.execute()

        return response?.body() as MemberDto
    }

    fun allSns() : ArrayList<SnsDto>{
        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(SnsService::class.java)
        val call = service?.allSns()
        val response = call?.execute()

        return response?.body() as ArrayList<SnsDto>
    }
}
