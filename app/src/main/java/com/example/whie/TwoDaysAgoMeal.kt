package com.example.whie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_two_days_ago_meal.*
import org.jetbrains.anko.startActivity

class TwoDaysAgoMeal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_days_ago_meal)

        //val meal=bundle.getParcelable<Meal>("yesterday_meal") as Meal
        val bundle = intent.getBundleExtra("bundle") as Bundle
        val mealTime = bundle.getString("meal_time") as String
        mealTimeText.text = mealTime

        rv_data_list.layoutManager = GridLayoutManager(this, 3)

        rv_data_list.adapter = ExtensionDataAdapter(meals, this) {
            makeBundleAndStartActivity(bundle, it)
        }
    }

    private fun makeBundleAndStartActivity(bundle: Bundle, meal: Meal) {
        bundle.putParcelable("two_days_ago_meal", meal)

        startActivity<ThreeDaysAgoMeal>("bundle" to bundle)
    }
}
