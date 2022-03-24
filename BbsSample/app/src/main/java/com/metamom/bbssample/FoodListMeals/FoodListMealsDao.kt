package com.metamom.bbssample.FoodListMeals


import com.metamom.bbssample.RetrofitClient
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface FoodListMealsService{
    //write 보내기
    @POST("/FoodListTest")
    fun FoodListTest(@Body dto:FoodListMealsDto) : Call<String>

    //받기
    @GET("/FoodListSelect")
    fun FoodListSelect():Call<List<FoodListMealsDto>>

    /*@Multipart
    @POST("/src/main/webapp/upload")
    Call<FileUploadResponse>*/

    //파일 업로드



}
class FoodListMealsDao {
    companion object {
        var foodListMealsDao: FoodListMealsDao? = null

        fun getInstance(): FoodListMealsDao {
            if (foodListMealsDao == null) {
                foodListMealsDao = FoodListMealsDao()
            }
            return foodListMealsDao!!
        }
    }
    fun FoodListTest(dto: FoodListMealsDto): String {
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit?.create(FoodListMealsService::class.java)
        val call = service?.FoodListTest(dto)
        val response = call?.execute()

        return response?.body() as String

    }
    fun FoodListSelect():ArrayList<FoodListMealsDto>{
        val retrofit = RetrofitClient.getInstance()
        val service = retrofit?.create(FoodListMealsService::class.java)
        val call = service?.FoodListSelect()
        val response = call?.execute()

        return response?.body() as ArrayList<FoodListMealsDto>
    }
}