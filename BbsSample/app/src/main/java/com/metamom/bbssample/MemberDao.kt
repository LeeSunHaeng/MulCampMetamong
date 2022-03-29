package com.metamom.bbssample

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface MemberService {

    // 7. 문자 보내기/ list 받기
    //@FormUrlEncoded
    @POST("/login")
    fun login(@Body dto: MemberDto): Call<MemberDto>

    @POST("/addmember")
    fun addmember(@Body dto: MemberDto): Call<String>


}


class MemberDao {

    // MemberDao의 싱글톤
    companion object {
        var memberdao: MemberDao? = null
        var user: MemberDto? = null

        fun getInstance(): MemberDao {
            if (memberdao == null) {
                memberdao = MemberDao()
            }
            return memberdao!!
        }
    }

    // list 받기
    fun login(dto : MemberDto) : MemberDto? {

        var response: Response<MemberDto>? = null
        try {
            val retrofit = RetrofitClient.getInstance()
            val service = retrofit?.create(MemberService::class.java)
            val call = service?.login(dto)
            response = call?.execute()
        }catch (e:Exception){
            response = null
        }
        if(response == null) return null

        return response?.body() as MemberDto
    }

    fun addmember(dto : MemberDto) : String? {

        var response: Response<String>?
        try {
            val retrofit = RetrofitClient.getInstance()
            val service = retrofit?.create(MemberService::class.java)
            val call = service?.addmember(dto)
            response = call?.execute()

        }catch (e:Exception){
            response = null
        }
        if(response == null) return null

        return response?.body() as String
    }

}

