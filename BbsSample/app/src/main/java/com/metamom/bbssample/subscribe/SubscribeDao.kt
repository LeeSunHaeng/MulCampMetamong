package com.metamom.bbssample.subscribe

import android.os.StrictMode
import android.util.Log
import com.metamom.bbssample.RetrofitClient
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/* #21# [구독] Dao */

interface SubscribeService {

    /* 구독 회원정보 가져오기 _getSubInfo(String id) → return Dto */
    @GET("/getSubInfo")
    fun getSubInfo(@Query("id") id:String): Call<SubscribeDto>
    /*@GET("/getSubInfo")
    fun getSubInfo(id: String) :Call<SubscribeDto>*/

    /* 구독 회원추가 _Back subAdd(SubscribeDto) → return String */
    @POST("/subAdd")
    fun subAdd(@Body dto: SubscribeDto) :Call<String>
}



class SubscribeDao {

    companion object {
        var subscribedao: SubscribeDao? = null
        var subUser: SubscribeDto? = null

        fun getInstance(): SubscribeDao {
            if (subscribedao == null){
                subscribedao = SubscribeDao()
            }
            return subscribedao!!
        }
    }


    /* #21# 구독 회원정보 가져오기 */
    fun getSubInfo(id :String) :SubscribeDto? {
        Log.d("SubscribeDao", "#21# SubscribeDao getSubInfo() 동작")
        Log.d("SubscribeDao", "#21# 현재 로그인한 사용자 id: ${id}")

        // Network 처리 (== httpURLConnection)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://172.30.1.25:3000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val service = retrofit.create(SubscribeService::class.java)

        // 문자열을 Back으로 보내고 Dto 받기
        var response: Response<SubscribeDto>? = null
        try {
            val call = service.getSubInfo(id)       // Back과 통신 _문자열 전달
            response = call.execute()               //            _Dto로 받기

            Log.d("SubscribeDao", "#21# SubscribeDao getSubInfo() Back으로부터 전달받은 구독 회원정보 확인 ${response.body()}")
        } catch (e:Exception) {
            response = null
        }

        if (response == null) return null

        return response.body() as SubscribeDto
    }

    /* #21# 구독 회원추가 (+ 멤버 DB 구독값 수정 _#Back에서 처리) */
    fun subAdd(dto :SubscribeDto) :String? {
        Log.d("SubscribeDao", "#21# 구독 신청을 위하여 Front에서 입력받은 Dto 값: ${dto.toString()}")

        var response: Response<String>? = null

        try {
            val retrofit = RetrofitClient.getInstance()
            val service = retrofit?.create(SubscribeService::class.java)
            val call = service?.subAdd(dto)
            response = call?.execute()
        }
        catch (e:Exception) {
            Log.d("SubscribeDao", "#21# SubscribeDao subAdd() try..catch 오류 > ${e.printStackTrace()}")
            response = null
        }

        if (response == null) return null
        return response.body() as String
    }

}