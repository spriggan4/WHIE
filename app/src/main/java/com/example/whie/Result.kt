package com.example.whie

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*
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
        Log.d("어제 먹은거", "Result meals = " + threeDayMeal[0])
        Log.d("그제 먹은거", "Result meals = " + threeDayMeal[1])
        Log.d("삼일전 먹은거", "Result meals = " + threeDayMeal[2])
        var selectedFoodTypeWithThreeDay = arrayOf(
            threeDayMeal[0].foodType.ordinal,
            threeDayMeal[1].foodType.ordinal,
            threeDayMeal[2].foodType.ordinal
        )
        var selectedFatTasteWithThreeDay = arrayOf(
            threeDayMeal[0].fatTaste.ordinal,
            threeDayMeal[1].fatTaste.ordinal,
            threeDayMeal[2].fatTaste.ordinal
        )
        var selectedTasteWithThreeDay = arrayOf(
            threeDayMeal[0].taste.ordinal,
            threeDayMeal[1].taste.ordinal,
            threeDayMeal[2].taste.ordinal
        )

        val pickedMeal = chooseRandomlyMealWithThreeDayMeal(
            pickedMealTime,
            selectedFoodTypeWithThreeDay,
            selectedFatTasteWithThreeDay,
            selectedTasteWithThreeDay
        )

        if (pickedMeal == null) {
            chooseRandomlyMealWithThreeDayMeal(
                pickedMealTime,
                selectedFoodTypeWithThreeDay,
                selectedFatTasteWithThreeDay,
                selectedTasteWithThreeDay
            )
        }

        Log.d("mealsSearched", "pickedMeal = " + pickedMeal)

        resultText.text = pickedMeal!!.foodName.toString()
    }

    fun chooseRandomlyMealWithThreeDayMeal(
        pickedMealTime: String,
        selectedFoodTypeWithThreeDay: Array<Int>,
        selectedFatTasteWithThreeDay: Array<Int>,
        selectedTasteWithThreeDay: Array<Int>
    ): Meal? {
        var pickedMeal: Meal? = null
        Log.d("mealsSearched", "FromStartMeal = " + pickedMeal)
        var numsForRandomFoodType = Array(FoodType.values().size, { 1 })
        var weightsForFoodType = arrayListOf<Int>()
        var numsForRandomFatTaste = Array(FatTaste.values().size, { 1 })
        var weightsForFatTaste = arrayListOf<Int>()
        var numsForRandomTaste = Array(Taste.values().size, { 1 })
        var weightsForTaste = arrayListOf<Int>()
        var mealsSearchedThroughMealtime = arrayListOf<Meal>()
        var mealSearchedThroughFoodType = arrayListOf<Meal>()
        var mealSearchedThroughFatTaste = arrayListOf<Meal>()
        var mealSearchedThroughTaste = arrayListOf<Meal>()

        //선택된 식사시간의 메뉴들 배열에 넣기
        searchThroughMealTime(pickedMealTime, mealsSearchedThroughMealtime)
        when (pickedMealTime) {
            MainMealTime.아침.toString()
            -> searchThroughMealTime(MainMealTime.아점.toString(), mealsSearchedThroughMealtime)

            MainMealTime.점심.toString()
            -> {
                searchThroughMealTime(MainMealTime.아점.toString(), mealsSearchedThroughMealtime)
                searchThroughMealTime(MainMealTime.점저.toString(), mealsSearchedThroughMealtime)
            }

            MainMealTime.저녁.toString()
            -> searchThroughMealTime(MainMealTime.점저.toString(), mealsSearchedThroughMealtime)
        }
        Log.d("mealsSearched", "식사시간 = " + mealsSearchedThroughMealtime)

        for (foodType in 0..selectedFoodTypeWithThreeDay.size - 1) {
            numsForRandomFoodType[selectedFoodTypeWithThreeDay[foodType]] += 1
        }
        //푸드타입은 enum 개수가 적으므로 곱해서 이미 선택받은 푸드타입은 나올 확률을 줄임.
        for (num in 0..numsForRandomFoodType.size - 1) {
            weightsForFoodType.add(FoodType.values().size * numMultipleWeight - numsForRandomFoodType[num])
        }

        for (fatTaste in 0..selectedFatTasteWithThreeDay.size - 1) {
            numsForRandomFatTaste[selectedFatTasteWithThreeDay[fatTaste]] += 1
        }
        for (num in 0..numsForRandomFatTaste.size - 1) {
            weightsForFatTaste.add(FatTaste.values().size * numMultipleWeight - numsForRandomFatTaste[num])
        }

        for (taste in 0..selectedTasteWithThreeDay.size - 1) {
            numsForRandomTaste[selectedTasteWithThreeDay[taste]] += 1
        }
        for (num in 0..numsForRandomTaste.size - 1) {
            weightsForTaste.add(Taste.values().size - numsForRandomTaste[num])
        }


        do {
            var pickedFoodType: FoodType =
                FoodType.values()[weighted_random(weightsForFoodType)]
            Log.d("mealsSearched", "선택받은 푸드타입 = " + pickedFoodType)
            //선택된 푸드타입의 음식들 배열에 넣기
            for (meal in mealsSearchedThroughMealtime) {
                if (meal.foodType == pickedFoodType)
                    mealSearchedThroughFoodType.add(meal)
            }
            Log.d("mealsSearched", "FoodType : " + mealSearchedThroughFoodType)

            var pickedFatTaste: FatTaste =
                FatTaste.values()[weighted_random(weightsForFatTaste)]
            Log.d("mealsSearched", "선택받은 기름기 = " + pickedFatTaste)
            //선택된 기름기의 음식들 배열에 넣기
            for (meal in mealSearchedThroughFoodType) {
                if (meal.fatTaste == pickedFatTaste)
                    mealSearchedThroughFatTaste.add(meal)
            }
            Log.d("mealsSearched", "FatTaste : " + mealSearchedThroughFatTaste)

            var pickedTaste: Taste = Taste.values()[weighted_random(weightsForTaste)]
            Log.d(
                "mealsSearched", "선택받은 맛 : " + pickedTaste
            )
            for (meal in mealSearchedThroughFatTaste) {
                if (meal.taste == pickedTaste)
                    mealSearchedThroughTaste.add(meal)
            }
            Log.d("mealsSearched", "Taste : " + mealSearchedThroughTaste)

            //배열에 아무것도 안들어 간다고 null이 되는게 아님
            if (mealSearchedThroughTaste.isNotEmpty()) {
                pickedMeal =
                    mealSearchedThroughTaste[Random.nextInt(0, mealSearchedThroughTaste.size)]
            } else {
                mealSearchedThroughFoodType.clear()
                mealSearchedThroughFatTaste.clear()
                mealSearchedThroughTaste.clear()
            }
            Log.d("mealsSearched", "meal = " + pickedMeal)
        } while (pickedMeal == null)

        return pickedMeal
    }

    fun weighted_random(weights: ArrayList<Int>): Int {
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