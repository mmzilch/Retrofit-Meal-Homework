package com.example.retrofitmealhomework.Api

import com.example.retrofitmealhomework.Model.MealByName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealApi {
    val mealApiInterface: MealApiInterface

    companion object {
        const val Base_URL = "https://www.themealdb.com/api/json/v1/1/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mealApiInterface = retrofit.create(MealApiInterface::class.java)
    }

    fun getMeal(f:String): Call<MealByName> {
       return mealApiInterface.getMeal(f)
    }

}