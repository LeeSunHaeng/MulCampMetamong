package com.metamom.bbssample.FoodListMeals


import com.metamom.bbssample.RetrofitClient
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodListMealsService{
    //write 보내기
    @POST("/FoodListTest")
    fun FoodListTest(@Body dto:FoodListMealsDto) : Call<String>

    //받기
    @GET("/FoodListSelect")
    fun FoodListSelect():Call<List<FoodListMealsDto>>

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