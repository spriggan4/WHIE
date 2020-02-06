package com.example.whie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_select_yesterday_meal.*
import org.jetbrains.anko.startActivity

class SelectYesterdayMeal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_yesterday_meal)

        //toString() 안붙여서 에러 났음.
        val mealTime = intent.getStringExtra("mealTime").toString()

        mealTimeText.text = mealTime

        samGyubSalText.setOnClickListener {
            makeBundleAndStartActivity(
                meals[0]
            )
        }

        duckMeatText.setOnClickListener {
            makeBundleAndStartActivity(
                meals[1]
            )
        }

        saSiMiText.setOnClickListener {
            makeBundleAndStartActivity(
                meals[2]
            )
        }

        suShiText.setOnClickListener {
            makeBundleAndStartActivity(
                meals[3]
            )
        }

        gamJaTangText.setOnClickListener {
            makeBundleAndStartActivity(
                meals[4]
            )
        }

        curryText.setOnClickListener {
            makeBundleAndStartActivity(
                meals[5]
            )
        }

        porkGalBiText.setOnClickListener {
            makeBundleAndStartActivity(
                meals[6]
            )
        }

        braisedChickenText.setOnClickListener {
            makeBundleAndStartActivity(
                meals[7]
            )
        }

        biBimBobText.setOnClickListener {
            makeBundleAndStartActivity(
                meals[8]
            )
        }

        pizzaText.setOnClickListener {
            makeBundleAndStartActivity(
                meals[9]
            )
        }

        gugSuText.setOnClickListener {
            makeBundleAndStartActivity(
                meals[10]
            )
        }

        calGugSuText.setOnClickListener {
            makeBundleAndStartActivity(
                meals[11]
            )
        }

        hamBurGerText.setOnClickListener {
            makeBundleAndStartActivity(
                meals[12]
            )
        }
    }

    private fun makeBundleAndStartActivity(meal: Meal) {
        var bundle = Bundle()
        bundle.putParcelable("yesterday_meal", meal)
        bundle.putString("meal_time", mealTimeText.text.toString())

        startActivity<TwoDaysAgoMeal>("bundle" to bundle)
    }
}