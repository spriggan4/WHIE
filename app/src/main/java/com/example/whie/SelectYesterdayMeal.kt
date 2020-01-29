package com.example.whie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_select_yesterday_meal.*
import org.jetbrains.anko.startActivity

class SelectYesterdayMeal : AppCompatActivity() {
    private val areaDivisions = arrayOf("Korea","West","Japan")
    private val foodTypes = arrayOf("Meat", "Bread", "Sasimi", "Noodle")
    private val mainMealTimes = arrayOf("Morning", "Lunch", "Dinner")
    private val tastes = arrayOf(
        "Oily", "LowOily", "SweetyAndSalty"
        , "Texture", "SpicyAndSalty", "Salty", "Soup", "HitsTheSpot"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_yesterday_meal)

        mealTimeText.text = intent.getStringExtra("mealTime").toString()

        samGyubSalText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "삼겹살", areaDivisions[0], foodTypes[0], mainMealTimes[2],
                    tastes[0]
                )
            )
        }

        duckMeatText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "오리고기", "Korea",
                    "Meat", "Dinner", "LowOily"
                )
            )
        }

        pizzaText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "피자", "West",
                    "Bread", "Dinner", "Oily"
                )
            )
        }

        porkGalBiText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "양념갈비", "Korea",
                    "Meat", "Dinner", "SweetyAndSalty"
                )
            )
        }

        saSiMiText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "회", "Japan",
                    "Sasimi", "Dinner", "Texture"
                )
            )
        }

        braisedChickenText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "찜닭", areaDivisions[0], foodTypes[0], mainMealTimes[2],
                    tastes[2]
                )
            )
        }
    }

    private fun makeBundleAndStartActivity(meal: Meal) {
        var bundle = Bundle()
        bundle.putParcelable("yesterday_meal", meal)

        startActivity<TwoDaysAgoMeal>("bundle" to bundle)
    }
}