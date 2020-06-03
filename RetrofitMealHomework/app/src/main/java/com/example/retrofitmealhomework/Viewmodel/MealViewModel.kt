package com.example.retrofitmealhomework.Viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitmealhomework.Api.MealApi
import com.example.retrofitmealhomework.Model.Meal
import com.example.retrofitmealhomework.Model.MealByName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel :ViewModel()
{
    val mealResult : MutableLiveData<List<Meal>> = MutableLiveData()
    val loading : MutableLiveData<Boolean> = MutableLiveData()
    val resultLoaderError : MutableLiveData<Boolean> = MutableLiveData()

    fun getMealResult() : LiveData<List<Meal>> = mealResult
    fun getError() : LiveData<Boolean> = resultLoaderError
    fun getLoading () : LiveData<Boolean> = loading

    private val mealApi : MealApi = MealApi()

    fun loadMeal(f: String)
    {
        loading.value = true
        val apiCall = mealApi.getMeal(f)
        apiCall.enqueue(object : Callback<MealByName>{
            override fun onFailure(call: Call<MealByName>, t: Throwable) {
                resultLoaderError.value = true
                loading.value = false
            }

            override fun onResponse(call: Call<MealByName>, response: Response<MealByName>) {
                response.isSuccessful.let {
                    loading.value = false
                    val mealList : List<Meal> = response.body()?.meals?: emptyList()
                    Log.d("meallist>>>",mealList.toString())
                    Log.d("responseBody>>>",response.body().toString())
                    mealResult.value = mealList
                }
            }

        })
    }

}