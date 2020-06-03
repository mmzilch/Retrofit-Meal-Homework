package com.example.retrofitmealhomework.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitmealhomework.Model.Meal
import com.example.retrofitmealhomework.Model.MealByName
import com.example.retrofitmealhomework.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_meal.view.*

class MealAdapter :RecyclerView.Adapter<MealAdapter.MealViewHolder>(){

    var mealResultList: List<Meal> = listOf()

    fun updateMealList(result : List<Meal>)
    {
        this.mealResultList = result
        notifyDataSetChanged()
    }

  /*  var data = ArrayList<Meal>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return MealViewHolder.from(parent)
    }

    override fun getItemCount(): Int = mealResultList.size

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val item = mealResultList[position]
        holder.bindMeal(item)
        Log.d("size>>>>", mealResultList.size.toString())
    }

    class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindMeal(meal: Meal){
            /*Picasso.get().load("https://www.themealdb.com/images/media/meals/${meal.strMealThumb}")
                .placeholder(R.drawable.profile).into(itemView.imageProfile)*/

            Picasso.get().load(meal.strMealThumb)
                .placeholder(R.drawable.profile).into(itemView.imageProfile)

            itemView.tvName.text = meal.strMeal
            itemView.tvName.isSelected=true
            itemView.tvCategory.text = meal.strCategory

            Log.d("Name>>>>",itemView.tvName.text.toString())
        }
        companion object{
            fun from(parent: ViewGroup): MealViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_meal,parent,false)
                return MealViewHolder(view)
            }
        }
    }
}