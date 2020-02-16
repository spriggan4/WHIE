package com.example.whie

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_select_meals_eat.*
import org.jetbrains.anko.startActivity


class SelectMealsEat : AppCompatActivity() {
    lateinit var searchView: SearchView
    //    lateinit var searchItem: MenuItem
    val mealsAfterSearch = arrayListOf<Meal>()
    var turnNum = 0
    var bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_meals_eat)


        rv_data_list.layoutManager = GridLayoutManager(this, 3)

        rv_data_list.adapter = ExtensionDataAdapter(meals, this) {
            makeBundleAndStartActivity(it)
        }

        mealRevertBtn.setOnClickListener {
            revertSelectedFood()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        val searchManager =
            getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search_meal).actionView as SearchView
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(true)
            setMaxWidth(Int.MAX_VALUE)
            queryHint = "음식명을 입력하세요"
            setImeOptions(EditorInfo.IME_ACTION_DONE)
            //검색 리스너 구현
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(s: String): Boolean {
                    searchForMeal(s)
                    return true
                }

                override fun onQueryTextSubmit(s: String): Boolean {
                    clearFocus()
                    onActionViewCollapsed()
                    rv_data_list.adapter = ExtensionDataAdapter(meals, this@SelectMealsEat) {
                        makeBundleAndStartActivity(it)
                    }
                    return false
                }
            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        Log.d("SelectMealsEat", "뒤로가기 후 두번째 액티비티 시작 bundle = " + bundle)
        turnNum=0
        bundle.clear()
        Log.d("SelectMealsEat", "뒤로가기 후 클리어하고 bundle = " + bundle)
        mealsEatenLayout.removeAllViews()
        super.onResume()
    }

    override fun onBackPressed() {
        searchView.setIconifiedByDefault(true)
        super.onBackPressed()
    }

//    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
//        return when(keyCode){
//            KeyEvent.KEYCODE_BACK->{
//                rv_data_list.adapter = ExtensionDataAdapter(meals, this) {
//                    makeBundleAndStartActivity(it)
//                }
//                true
//            }
//            else -> super.onKeyUp(keyCode, event)
//        }
//
//    }

    private fun makeBundleAndStartActivity(meal: Meal) {
        val textView = TextView(this)
        textView.text = "${turnNum + 1}. ${meal.foodName}"

        mealsEatenLayout.addView(textView, turnNum)

        when (turnNum) {
            0 -> {
                bundle.putParcelable("yesterday_meal", meal)
                turnNum++
            }
            1 -> {
                bundle.putParcelable("two_days_ago_meal", meal)
                turnNum++
            }
            2 -> {
                bundle.putParcelable("three_days_ago_meal", meal)
                bundle.putString("meal_time", intent.getStringExtra("mealTime").toString())
                startActivity<Result>("bundle" to bundle)
            }
        }
    }

    private fun revertSelectedFood() {
        if (turnNum > 0) {
            when (turnNum) {
                2 -> {
                    bundle.remove("two_days_ago_meal")
                    //Log.d("mealsInBundle", "turnNum = " + turnNum + " bundle = " + bundle)
                }
                1 -> {
                    bundle.remove("yesterday_meal")
                    //Log.d("mealsInBundle", "turnNum = " + turnNum + " bundle = " + bundle)
                }
            }
        }
        mealsEatenLayout.removeViewAt(--turnNum)
    }

    private fun searchForMeal(charText: String) {
        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        mealsAfterSearch.clear()
        //Log.d("InputText", "InputText = " + charText)

        if (charText.length != 0) { // 리스트의 모든 데이터를 검색한다.
            Log.d("InputText", "If안에 들어옴")
            for (i in 0 until meals.size) { // foodNameList의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
//                Log.d(
//                    "InputText",
//                    "검색된 내용 = " + meals.get(i).foodName.toString().toLowerCase().contains(charText)
//                )
                if (meals.get(i).foodName.toString().toLowerCase().contains(charText)) { // 검색된 데이터를 리스트에 추가한다.
                    mealsAfterSearch.add(meals[i])
                    //Log.d("InputText", "mealsAfterSearch = " + mealsAfterSearch)
                }
            }
            rv_data_list.adapter = ExtensionDataAdapter(mealsAfterSearch, this) {
                makeBundleAndStartActivity(it)
            }
        }
        else {
            rv_data_list.adapter = ExtensionDataAdapter(meals, this) {
                makeBundleAndStartActivity(it)
            }
            // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
            //adapter.notifyDataSetChanged()
        }

    }
}
