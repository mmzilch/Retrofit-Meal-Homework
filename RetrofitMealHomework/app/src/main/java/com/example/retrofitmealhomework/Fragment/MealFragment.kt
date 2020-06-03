package com.example.retrofitmealhomework.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitmealhomework.Adapter.MealAdapter

import com.example.retrofitmealhomework.R
import com.example.retrofitmealhomework.Viewmodel.MealViewModel
import kotlinx.android.synthetic.main.fragment_meal.*

/**
 * A simple [Fragment] subclass.
 */
class MealFragment : Fragment() {

    private lateinit var mealAdapter: MealAdapter
    private lateinit var mealViewModel: MealViewModel
    private lateinit var viewManager : RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(context)
        mealAdapter = MealAdapter()
        rcyMeal.apply {
            adapter = mealAdapter
            layoutManager = viewManager
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        mealViewModel = ViewModelProvider(this).get(MealViewModel::class.java)

        mealViewModel.mealResult.observe(
            viewLifecycleOwner, Observer {
                rcyMeal.visibility = View.VISIBLE
                tvError.visibility = View.GONE
                mealAdapter.updateMealList(it)
                //mealAdapter.data
                Log.d("UpdateList>>>",it.toString())
            }
        )

        mealViewModel.getError().observe(
            viewLifecycleOwner, Observer {
                if (it)
                {
                    tvError.visibility = View.VISIBLE
                    rcyMeal.visibility = View.GONE
                }

                else
                {
                    tvError.visibility = View.GONE
                    rcyMeal.visibility = View.VISIBLE
                }
            }
        )
    }

    override fun onResume() {
        super.onResume()
        mealViewModel.loadMeal("a")
    }
}
