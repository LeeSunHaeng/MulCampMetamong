package com.example.bbssample

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.lang.Exception

interface MemberService {

    // 7. 문자 보내기/ list 받기
    @POST("/login")
    fun login(@Body dto:MemberDto): Call<MemberDto>
}

class MemberDao {

    // MemberDao의 싱글톤
    companion object {
        var memberdao: MemberDao? = null
        var user:MemberDto? = null

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
}

