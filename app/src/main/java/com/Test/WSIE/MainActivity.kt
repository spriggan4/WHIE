package com.Test.WSIE

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
        //현재 시간을 통해서 만들기
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            Toast.makeText(
                this,
                "'뒤로' 버튼을 한번 더 누르시면 종료됩니다", Toast.LENGTH_SHORT
            ).show()
            //토스트만 뛰우고 일단 함수 중료
            return
        }
        //2초안에 다시 누르면 앱종료
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish()
        }
    }

    fun inputYesterdayMeal() {
        startActivity<SelectMealsEat>("mealTime" to mealTime)
    }
}
