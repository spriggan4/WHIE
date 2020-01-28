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
            mealTime = "아침"
            inputYesterdayMeal()
        }
        lunchBtn.setOnClickListener {
            mealTime = "점심"
            inputYesterdayMeal()
        }
        dinnerBtn.setOnClickListener {
            mealTime = "저녁"
            inputYesterdayMeal()
        }
    }

    fun inputYesterdayMeal() {
        startActivity<SelectYesterdayMeal>("mealTime" to mealTime)
    }
}
