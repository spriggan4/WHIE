package com.example.whie

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_select_meals_eat.*
import org.jetbrains.anko.startActivity

class SelectMealsEat : AppCompatActivity() {
    var turnNum = 0
    var bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_meals_eat)

        rv_data_list.layoutManager = GridLayoutManager(this, 3)

        rv_data_list.adapter = ExtensionDataAdapter(meals, this) {
            makeBundleAndStartActivity(it)
        }
    }

    private fun makeBundleAndStartActivity(meal: Meal) {
        val textView = TextView(this)
        textView.text = "${turnNum+1}. ${meal.foodName.toString()}"

        mealsEatenLayout.addView(textView, turnNum)

        when (turnNum) {
            0 -> {
                bundle.putParcelable("yesterday_meal", meal)
                turnNum++
            }
            1 -> {
                bundle.putParcelable("two_days_ago_meal", meal)
                turnNum++
            }
            2 -> {
                bundle.putParcelable("three_days_ago_meal", meal)
                bundle.putString("meal_time", intent.getStringExtra("mealTime").toString())
                startActivity<Result>("bundle" to bundle)
            }
        }
    }
}
