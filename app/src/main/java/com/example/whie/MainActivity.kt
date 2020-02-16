package com.example.whie

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mealTime: String
    private var backKeyPressedTime = 0L

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

    override fun onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            Toast.makeText(
                this,
                "'뒤로' 버튼을 한번 더 누르시면 종료됩니다", Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish()
        }
        //super.onBackPressed()
    }

    fun inputYesterdayMeal() {
        startActivity<SelectMealsEat>("mealTime" to mealTime)
    }
}
