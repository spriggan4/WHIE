package com.example.whie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_select_yesterday_meal.*
import org.jetbrains.anko.startActivity

class SelectYesterdayMeal : AppCompatActivity() {
    private lateinit var meal: Meal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_yesterday_meal)

        mealTimeText.text = intent.getStringExtra("mealTime").toString()

        samGyubSalText.setOnClickListener {
            meal = Meal("삼겹살", "Korea", "Meat", "Dinner", "Oily")

            var bundle = Bundle()
            bundle.putParcelable("selected_meal", meal)

            startActivity<TwoDaysAgoMeal>("bundle" to bundle)
        }
    }
}