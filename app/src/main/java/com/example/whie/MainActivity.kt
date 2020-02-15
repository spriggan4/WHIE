package com.example.whie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mealTime: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        morningBtn.setOnClickListener {
            mealTime = MainMealTime.아침.toString()
            inputYesterdayMeal()
        }
        lunchBtn.setOnClickListener {
            mealTime = MainMealTime.점심.toString()
            inputYesterdayMeal()
        }
        dinnerBtn.setOnClickListener {
            mealTime = MainMealTime.저녁.toString()
            inputYesterdayMeal()
        }
    }

    fun inputYesterdayMeal() {
        startActivity<SelectMealsEat>("mealTime" to mealTime)
    }
}
