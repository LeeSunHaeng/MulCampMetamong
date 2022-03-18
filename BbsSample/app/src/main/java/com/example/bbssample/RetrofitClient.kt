package com.example.bbssample

import android.os.StrictMode
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitClient {
    companion object {
        private var instance: Retrofit? = null

        fun getInstance(): Retrofit? {
            if(instance == null) {
                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)

                val gson = GsonBuilder()
                    .setLenient()
                    .create()

                instance = Retrofit.Builder()
                    .baseUrl("http://172.30.1.254:3000/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build()
            }
            return instance!!
        }

        // 문자열을 전송받을 시에 사용한다
        private var instanceStr: Retrofit? = null
        fun getInstanceStr(): Retrofit? {
            if(instanceStr == null) {
                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)

                // ScalarsConverterFactory 만을 사용해야 한다
                instanceStr = Retrofit.Builder()
                    .baseUrl("http://70.12.113.169:3000/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build()//주석
            }
            return instanceStr!!
        }

    }
}