package com.example.whie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_select_yesterday_meal.*
import org.jetbrains.anko.startActivity

class SelectYesterdayMeal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_yesterday_meal)

        mealTimeText.text = intent.getStringExtra("mealTime").toString()

        samGyubSalText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "삼겹살",
                    AreaDivision.KOREA.ordinal,
                    FoodType.MEAT.ordinal,
                    MainMealTime.DINNER.ordinal,
                    Taste.OILY.ordinal
                )
            )
        }

        duckMeatText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "오리고기",
                    AreaDivision.KOREA.ordinal,
                    FoodType.MEAT.ordinal,
                    MainMealTime.DINNER.ordinal,
                    Taste.LOW_OILY.ordinal
                )
            )
        }

        saSiMiText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "회",
                    AreaDivision.JAPAN.ordinal,
                    FoodType.SASIMI.ordinal,
                    MainMealTime.DINNER.ordinal,
                    Taste.TEXTURE.ordinal
                )
            )
        }

        suShiText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "초밥",
                    AreaDivision.JAPAN.ordinal,
                    FoodType.SASIMI.ordinal,
                    MainMealTime.DINNER.ordinal,
                    Taste.TEXTURE.ordinal,
                    Taste.RICE.ordinal,
                    Taste.SWEETY_AND_SALTY.ordinal
                )
            )
        }

        gamJaTangText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "감자탕",
                    AreaDivision.KOREA.ordinal,
                    FoodType.MEAT_SOUP.ordinal,
                    MainMealTime.DINNER.ordinal,
                    Taste.SOUP.ordinal,
                    Taste.LOW_OILY.ordinal
                )
            )
        }

        curryText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "카레",
                    AreaDivision.ETC.ordinal,
                    FoodType.RICE.ordinal,
                    MainMealTime.LUNCH.ordinal,
                    Taste.HOT.ordinal,
                    Taste.PEPPERY.ordinal
                )
            )
        }

        porkGalBiText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "돼지갈비",
                    AreaDivision.KOREA.ordinal,
                    FoodType.MEAT.ordinal,
                    MainMealTime.DINNER.ordinal,
                    Taste.SWEETY_AND_SALTY.ordinal,
                    Taste.LOW_OILY.ordinal
                )
            )
        }

        braisedChickenText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "찜닭",
                    AreaDivision.KOREA.ordinal,
                    FoodType.MEAT.ordinal,
                    MainMealTime.DINNER.ordinal,
                    Taste.SWEETY_AND_SALTY.ordinal,
                    Taste.LOW_OILY.ordinal
                )
            )
        }

        biBimBobText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "비빔밥",
                    AreaDivision.KOREA.ordinal,
                    FoodType.RICE.ordinal,
                    MainMealTime.ANYTIME.ordinal,
                    Taste.RICE.ordinal,
                    Taste.HOT.ordinal,
                    Taste.ADD_FLAVOR.ordinal
                )
            )
        }

        pizzaText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "피자",
                    AreaDivision.WEST.ordinal,
                    FoodType.BREAD.ordinal,
                    MainMealTime.DINNER.ordinal,
                    Taste.CHEESY.ordinal
                )
            )
        }

        gugSuText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "국수",
                    AreaDivision.KOREA.ordinal,
                    FoodType.NOODLE.ordinal,
                    MainMealTime.LUNCH.ordinal,
                    Taste.SOUP.ordinal,
                    Taste.SOY_SAUCE.ordinal
                )
            )
        }

        calGugSuText.setOnClickListener{
            makeBundleAndStartActivity(
                Meal(
                    "칼국수",
                    AreaDivision.KOREA.ordinal,
                    FoodType.NOODLE.ordinal,
                    MainMealTime.LUNCH.ordinal,
                    Taste.SOUP.ordinal,
                    Taste.HITS_THE_SPOT.ordinal
                )
            )
        }

        hamBurGerText.setOnClickListener {
            makeBundleAndStartActivity(
                Meal(
                    "햄버거",
                    AreaDivision.WEST.ordinal,
                    FoodType.BREAD.ordinal,
                    MainMealTime.LUNCH.ordinal,
                    Taste.OILY.ordinal,
                    Taste.SWEETY_AND_SALTY.ordinal
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