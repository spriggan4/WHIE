package com.example.whie

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class Result : AppCompatActivity() {
    val numMultipleWeight = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        val bundle = intent.getBundleExtra("bundle") as Bundle
        val pickedMealTime = bundle.getString("meal_time") as String
        val threeDayMeal = arrayOf<Meal>(
            bundle.getParcelable<Meal>("yesterday_meal") as Meal,
            bundle.getParcelable<Meal>("two_days_ago_meal") as Meal,
            bundle.getParcelable<Meal>("three_days_ago_meal") as Meal
        )
        showRecommendedMenu(threeDayMeal, pickedMealTime)
    }

    fun showRecommendedMenu(threeDayMeal: Array<Meal>, pickedMealTime: String) {
//        var selectedAreas = arrayOf(
//            threeDayMeal[0].areaDivision.ordinal,
//            threeDayMeal[1].areaDivision.ordinal,
//            threeDayMeal[2].areaDivision.ordinal
//        )
        var selectedFoodType = arrayOf(
            threeDayMeal[0].foodType.ordinal,
            threeDayMeal[1].foodType.ordinal,
            threeDayMeal[2].foodType.ordinal
        )
        var selectedFatTaste = arrayOf(
            threeDayMeal[0].fatTaste.ordinal,
            threeDayMeal[1].fatTaste.ordinal,
            threeDayMeal[2].fatTaste.ordinal
        )
        var selectedTaste = arrayOf(
            threeDayMeal[0].taste.ordinal,
            threeDayMeal[1].taste.ordinal,
            threeDayMeal[2].taste.ordinal
        )
//        var selectedTaste3 = arrayOf(
//            threeDayMeal[0].taste3.ordinal,
//            threeDayMeal[1].taste3.ordinal,
//            threeDayMeal[2].taste3.ordinal
//        )

//        var numsForRandomArea = Array(AreaDivision.values().size, { 1 })
//        for (i in 0..selectedAreas.size - 1) {
//            numsForRandomArea[selectedAreas[i]] += 1
//        }
//        val weightsForArea = arrayOf(
//            AreaDivision.values().size * numMultipleWeight - numsForRandomArea[0],
//            AreaDivision.values().size * numMultipleWeight - numsForRandomArea[1],
//            AreaDivision.values().size * numMultipleWeight - numsForRandomArea[2],
//            AreaDivision.values().size * numMultipleWeight - numsForRandomArea[3]
//        )
//        val pickedArea: AreaDivision = AreaDivision.values()[
//                weighted_random(weightsForArea)
//        ]

        var numsForRandomFoodType = Array(FoodType.values().size, { 1 })
        for (i in 0..selectedFoodType.size - 1) {
            numsForRandomFoodType[selectedFoodType[i]] += 1
        }
        val weightsForFoodType = arrayOf(
            FoodType.values().size * numMultipleWeight - numsForRandomFoodType[0],
            FoodType.values().size * numMultipleWeight - numsForRandomFoodType[1],
            FoodType.values().size * numMultipleWeight - numsForRandomFoodType[2],
            FoodType.values().size * numMultipleWeight - numsForRandomFoodType[3],
            FoodType.values().size * numMultipleWeight - numsForRandomFoodType[4],
            FoodType.values().size * numMultipleWeight - numsForRandomFoodType[5]
        )
        val pickedFoodType: FoodType = FoodType.values()[
                weighted_random(weightsForFoodType)
        ]

        var numsForRandomFatTaste = Array(Taste.values().size, { 1 })
        for (i in 0..selectedFatTaste.size - 1) {
            numsForRandomFatTaste[selectedFatTaste[i]] += 1
        }
        val weightsForFatTaste = arrayOf(
            Taste.values().size * numMultipleWeight - numsForRandomFatTaste[0],
            Taste.values().size * numMultipleWeight - numsForRandomFatTaste[1],
            Taste.values().size * numMultipleWeight - numsForRandomFatTaste[2],
            Taste.values().size * numMultipleWeight - numsForRandomFatTaste[3],
            Taste.values().size * numMultipleWeight - numsForRandomFatTaste[4],
            Taste.values().size * numMultipleWeight - numsForRandomFatTaste[5],
            Taste.values().size * numMultipleWeight - numsForRandomFatTaste[6],
            Taste.values().size * numMultipleWeight - numsForRandomFatTaste[7],
            Taste.values().size * numMultipleWeight - numsForRandomFatTaste[8],
            Taste.values().size * numMultipleWeight - numsForRandomFatTaste[9],
            Taste.values().size * numMultipleWeight - numsForRandomFatTaste[10],
            Taste.values().size * numMultipleWeight - numsForRandomFatTaste[11],
            Taste.values().size * numMultipleWeight - numsForRandomFatTaste[12],
            Taste.values().size * numMultipleWeight - numsForRandomFatTaste[13],
            Taste.values().size * numMultipleWeight - numsForRandomFatTaste[14],
            Taste.values().size * numMultipleWeight - numsForRandomFatTaste[15],
            Taste.values().size * numMultipleWeight - numsForRandomFatTaste[16]
        )
        val pickedFatTaste: Taste = Taste.values()[
                weighted_random(weightsForFatTaste)
        ]

        var numsForRandomTaste = Array(Taste.values().size, { 1 })
        for (i in 0..selectedTaste.size - 1) {
            numsForRandomTaste[selectedTaste[i]] += 1
        }
        val weightsForTaste = arrayOf(
            Taste.values().size * numMultipleWeight - numsForRandomTaste[0],
            Taste.values().size * numMultipleWeight - numsForRandomTaste[1],
            Taste.values().size * numMultipleWeight - numsForRandomTaste[2],
            Taste.values().size * numMultipleWeight - numsForRandomTaste[3],
            Taste.values().size * numMultipleWeight - numsForRandomTaste[4],
            Taste.values().size * numMultipleWeight - numsForRandomTaste[5],
            Taste.values().size * numMultipleWeight - numsForRandomTaste[6],
            Taste.values().size * numMultipleWeight - numsForRandomTaste[7],
            Taste.values().size * numMultipleWeight - numsForRandomTaste[8],
            Taste.values().size * numMultipleWeight - numsForRandomTaste[9],
            Taste.values().size * numMultipleWeight - numsForRandomTaste[10],
            Taste.values().size * numMultipleWeight - numsForRandomTaste[11],
            Taste.values().size * numMultipleWeight - numsForRandomTaste[12],
            Taste.values().size * numMultipleWeight - numsForRandomTaste[13],
            Taste.values().size * numMultipleWeight - numsForRandomTaste[14],
            Taste.values().size * numMultipleWeight - numsForRandomTaste[15],
            Taste.values().size * numMultipleWeight - numsForRandomTaste[16]
        )

        var pickedTaste: Taste? = null
        pickedTaste = Taste.values()[
                weighted_random(weightsForTaste)
        ]

//        var numsForRandomTaste3 = Array(Taste.values().size, { 1 })
//        for (i in 0..selectedTaste3.size - 1) {
//            numsForRandomTaste3[selectedTaste3[i]] += 1
//        }
//        val weightsForTaste3 = arrayOf(
//            Taste.values().size * numMultipleWeight - numsForRandomTaste3[0],
//            Taste.values().size * numMultipleWeight - numsForRandomTaste3[1],
//            Taste.values().size * numMultipleWeight - numsForRandomTaste3[2],
//            Taste.values().size * numMultipleWeight - numsForRandomTaste3[3],
//            Taste.values().size * numMultipleWeight - numsForRandomTaste3[4],
//            Taste.values().size * numMultipleWeight - numsForRandomTaste3[5],
//            Taste.values().size * numMultipleWeight - numsForRandomTaste3[6],
//            Taste.values().size * numMultipleWeight - numsForRandomTaste3[7],
//            Taste.values().size * numMultipleWeight - numsForRandomTaste3[8],
//            Taste.values().size * numMultipleWeight - numsForRandomTaste3[9],
//            Taste.values().size * numMultipleWeight - numsForRandomTaste3[10],
//            Taste.values().size * numMultipleWeight - numsForRandomTaste3[11],
//            Taste.values().size * numMultipleWeight - numsForRandomTaste3[12],
//            Taste.values().size * numMultipleWeight - numsForRandomTaste3[13],
//            Taste.values().size * numMultipleWeight - numsForRandomTaste3[14],
//            Taste.values().size * numMultipleWeight - numsForRandomTaste3[15],
//            Taste.values().size * numMultipleWeight - numsForRandomTaste3[16]
//        )
//
//
//        var pickedTaste3: Taste? = null
//        do {
//            pickedTaste3 = Taste.values()[
//                    weighted_random(weightsForTaste3)
//            ]
//        } while (pickedTaste3 == pickedTaste2)
        Log.d(
            "mealsSearched", " 음식종류 : " + pickedFoodType + " 기름기 : " +
                    pickedFatTaste + " 맛 : " + pickedTaste
        )

        val mealsSearchedThroughMealtime = arrayListOf<Meal>()
        when (pickedMealTime) {
            MainMealTime.아침.toString()
            -> searchThroughMealTime(pickedMealTime, mealsSearchedThroughMealtime)
            else
            -> {
                searchThroughMealTime(pickedMealTime, mealsSearchedThroughMealtime)
                searchThroughMealTime(MainMealTime.점저.toString(), mealsSearchedThroughMealtime)
            }
        }

        Log.d("mealsSearched", "식사시간 = " + mealsSearchedThroughMealtime)

//        val mealSearchedThroughArea = arrayListOf<Meal>()
////        for (meal in mealsSearchedThroughMealtime) {
////            if (meal.areaDivision == pickedArea)
////                mealSearchedThroughArea.add(meal)
////        }

        //Log.d("mealsSearched", "지역 = " + mealSearchedThroughArea)

        val mealSearchedThroughFoodType = arrayListOf<Meal>()
        for (meal in mealsSearchedThroughMealtime) {
            if (meal.foodType == pickedFoodType)
                mealSearchedThroughFoodType.add(meal)
        }

        Log.d("mealsSearched", "FoodType : " + mealSearchedThroughFoodType)
    }

    fun weighted_random(weights: Array<Int>): Int {
        var r = Random.nextInt(1, weights.sum())
        for (i in 0..weights.size) {
            r -= weights[i]
            if (r < 1) return i
        }
        return error("상수 구현 실패")
    }

    inline fun searchThroughMealTime(
        pickedMealTime: String,
        mealsSearchedThroughMealtime: ArrayList<Meal>
    ) {
        for (meal in meals) {
            if (meal.mainMealTime.toString() == pickedMealTime)
                mealsSearchedThroughMealtime.add(meal)
        }
    }
}