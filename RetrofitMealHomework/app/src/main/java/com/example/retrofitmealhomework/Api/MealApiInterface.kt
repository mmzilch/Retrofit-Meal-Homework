package com.example.retrofitmealhomework.Api

import com.example.retrofitmealhomework.Model.MealByName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiInterface {
    @GET("search.php")
    fun getMeal(@Query("f") f : String): Call<MealByName>
}