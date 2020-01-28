package com.example.whie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_two_days_ago_meal.*

class TwoDaysAgoMeal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_days_ago_meal)

        val bundle=intent.getBundleExtra("bundle")
        val meal=bundle.getParcelable<Meal>("selected_meal") as Meal

        textView.text=meal.foodName
    }
}
