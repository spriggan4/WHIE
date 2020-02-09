package com.example.whie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_select_yesterday_meal.*
import org.jetbrains.anko.startActivity

class SelectYesterdayMeal : AppCompatActivity() {
    //val dataArray = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_yesterday_meal)

        //toString() 안붙여서 에러 났음.
        val mealTime = intent.getStringExtra("mealTime").toString()

        mealTimeText.text = mealTime

        rv_data_list.layoutManager = GridLayoutManager(this, 3)

        rv_data_list.adapter = ExtensionDataAdapter(meals, this) {
            makeBundleAndStartActivity(it)
        }
    }

    private fun makeBundleAndStartActivity(meal: Meal) {
        var bundle = Bundle()
        bundle.putParcelable("yesterday_meal", meal)
        bundle.putString("meal_time", mealTimeText.text.toString())

        rv_data_list.adapter = null
        startActivity<TwoDaysAgoMeal>("bundle" to bundle)
    }
}