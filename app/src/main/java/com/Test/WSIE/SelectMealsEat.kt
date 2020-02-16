package com.Test.WSIE

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
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton


class SelectMealsEat : AppCompatActivity() {
    lateinit var searchView: SearchView
    //    lateinit var searchItem: MenuItem
    val mealsAfterSearch = arrayListOf<Meal>()
    var turnNum = 0
    var bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_meals_eat)


        //한줄에 3개씩 뛰워짐
        rv_data_list.layoutManager = GridLayoutManager(this, 3)

        //ArrayList와 함수를 던져서 각각의 뷰가 실행시킬 리스너 설정
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
            //true면 아이콘 모양으로 false면 텍스트 입력 대기 상태
            setIconifiedByDefault(true)
            //텍스트 입력 창의 폭 설정
            setMaxWidth(Int.MAX_VALUE)
            //텍스트 입력 없으면 나오는 문구
            queryHint = "음식명을 입력하세요"
            //소프트키보드에서 완료 버튼 설정
            setImeOptions(EditorInfo.IME_ACTION_DONE)
            //검색 리스너 구현
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                //글자 입력마다 실행됨
                override fun onQueryTextChange(s: String): Boolean {
                    searchForMeal(s)
                    return true
                }

                //완료 버튼 누르면 실행됨.
                override fun onQueryTextSubmit(s: String): Boolean {
                    //소프트키보드 내려감
                    clearFocus()
                    //뷰를 축소 시킴
                    onActionViewCollapsed()
                    //완료 버튼 누르면 그리드 레이아웃을 복구시킴
                    rv_data_list.adapter = ExtensionDataAdapter(meals, this@SelectMealsEat) {
                        makeBundleAndStartActivity(it)
                    }
                    //쿼리가 리스너에서 처리될 경우 true를 서치뷰로 넘길 경우 false라는데 이해 다 못함.
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
        turnNum = 0
        bundle.clear()
        Log.d("SelectMealsEat", "뒤로가기 후 클리어하고 bundle = " + bundle)
        mealsEatenLayout.removeAllViews()
        //searchView.textView().text=""
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

        mealsEatenLayout.addView(textView, turnNum++)

        when (turnNum) {
            1 -> {
                bundle.putParcelable("yesterday_meal", meal)
            }
            2 -> {
                bundle.putParcelable("two_days_ago_meal", meal)
            }
            3 -> {
                alert("추천 메뉴를 보여드릴까요") {
                    yesButton {
                        bundle.putParcelable("three_days_ago_meal", meal)
                        bundle.putString("meal_time", intent.getStringExtra("mealTime").toString())
                        startActivity<Result>("bundle" to bundle)
                    }
                    noButton {
                        revertSelectedFood()
                    }
                }.show()

            }
        }
    }

    private fun revertSelectedFood() {
        if (turnNum > 0) {
            when (turnNum) {
                3 -> {

                }
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

        if (charText.length != 0) {
            // 리스트의 모든 데이터를 검색한다.
            for (i in 0 until meals.size) {
                // foodNameList의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (meals.get(i).foodName.toString().toLowerCase().contains(charText)) {
                    // 검색된 데이터를 mealsAfterSearch에 추가한다.
                    mealsAfterSearch.add(meals[i])
                }
            }
            rv_data_list.adapter = ExtensionDataAdapter(mealsAfterSearch, this) {
                makeBundleAndStartActivity(it)
            }
        } else {
            //문자 입력이 없으면 원상복구
            rv_data_list.adapter = ExtensionDataAdapter(meals, this) {
                makeBundleAndStartActivity(it)
            }
        }
    }
}
