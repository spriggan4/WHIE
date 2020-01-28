package com.example.whie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_select_yesterday_meal.*

class SelectYesterdayMeal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_yesterday_meal)

        mealTimeText.text = intent.getStringExtra("mealTime").toString()
    }
}
