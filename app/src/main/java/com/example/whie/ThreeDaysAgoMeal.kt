package com.example.whie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_select_yesterday_meal.*
import org.jetbrains.anko.startActivity

class ThreeDaysAgoMeal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three_days_ago_meal)

        val bundle = intent.getBundleExtra("bundle") as Bundle
        val mealTime = bundle.getString("meal_time") as String
        mealTimeText.text = mealTime

        samGyubSalText.setOnClickListener {
            makeBundleAndStartActivity(
                bundle, meals[0]
            )
        }

        duckMeatText.setOnClickListener {
            makeBundleAndStartActivity(
                bundle,meals[1]
            )
        }

        saSiMiText.setOnClickListener {
            makeBundleAndStartActivity(
                bundle,meals[2]
            )
        }

        suShiText.setOnClickListener {
            makeBundleAndStartActivity(
                bundle,meals[3]
            )
        }

        gamJaTangText.setOnClickListener {
            makeBundleAndStartActivity(
                bundle,meals[4]
            )
        }

        curryText.setOnClickListener {
            makeBundleAndStartActivity(
                bundle,meals[5]
            )
        }

        porkGalBiText.setOnClickListener {
            makeBundleAndStartActivity(
                bundle,meals[6]
            )
        }

        braisedChickenText.setOnClickListener {
            makeBundleAndStartActivity(
                bundle,meals[7]
            )
        }

        biBimBobText.setOnClickListener {
            makeBundleAndStartActivity(
                bundle,meals[8]
            )
        }

        pizzaText.setOnClickListener {
            makeBundleAndStartActivity(
                bundle,meals[9]
            )
        }

        gugSuText.setOnClickListener {
            makeBundleAndStartActivity(
                bundle,meals[10]
            )
        }

        calGugSuText.setOnClickListener {
            makeBundleAndStartActivity(
                bundle,meals[11]
            )
        }

        hamBurGerText.setOnClickListener {
            makeBundleAndStartActivity(
                bundle,meals[12]
            )
        }
    }

    private fun makeBundleAndStartActivity(bundle:Bundle,meal: Meal) {
        bundle.putParcelable("three_days_ago_meal", meal)

        startActivity<Result>("bundle" to bundle)
    }
}
