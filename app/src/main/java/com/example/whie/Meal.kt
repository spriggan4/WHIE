package com.example.whie

import android.os.Parcel
import android.os.Parcelable

//enum class AreaDivision { KOREA, WEST, JAPAN, ETC }
enum class FoodName {
    삼겹살, 오리고기, 피자, 양념갈비, 회, 간장찜닭, 초밥, 감자탕, 국수, 칼국수, 햄버거, 짬뽕,
    짜장면, 라면, 토스트, 토마토_스파게티, 우동, 탕수육, 된장찌개, 김치찌개, 제육덮밥, 고등어구이, 참치회, 낚지볶음,
    갈치구이, 보쌈, 아구찜, 카레, 비빔밥, 볶음밥
}
enum class FoodType { MEAT, MEAT_SOUP, BREAD, SASIMI, NOODLE, RICE, SEA_FOOD }
enum class MainMealTime { 아침, 아점, 점심, 저녁, 점저 }
enum class FatTaste {
    OILY, LOW_OILY, MIDDLE, LIGHT
}
enum class Taste {
    SWEETY_AND_SALTY, SAVORY, SALTY, SWEETY, HOT, SPICY, HITS_THE_SPOT, TEXTURE, ADD_FLAVOR
}

data class Meal(
    val foodName: FoodName,
    val foodType: FoodType,
    val mainMealTime: MainMealTime,
    val fatTaste: FatTaste,
    val taste: Taste
) : Parcelable {
    constructor(source: Parcel) : this(
        FoodName.values()[source.readInt()],
        FoodType.values()[source.readInt()],
        MainMealTime.values()[source.readInt()],
        FatTaste.values()[source.readInt()],
        Taste.values()[source.readInt()]
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(foodName.ordinal)
        //writeInt(areaDivision.ordinal)
        writeInt(foodType.ordinal)
        writeInt(mainMealTime.ordinal)
        writeInt(fatTaste.ordinal)
        writeInt(taste.ordinal)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Meal> = object : Parcelable.Creator<Meal> {
            override fun createFromParcel(source: Parcel): Meal = Meal(source)
            override fun newArray(size: Int): Array<Meal?> = arrayOfNulls(size)
        }
    }
}

val meals = arrayListOf(
    Meal(
        FoodName.삼겹살,
        //AreaDivision.KOREA,
        FoodType.MEAT,
        MainMealTime.저녁,
        FatTaste.OILY,
        Taste.ADD_FLAVOR
    ),
    Meal(
        FoodName.오리고기,
        //AreaDivision.KOREA,
        FoodType.MEAT,
        MainMealTime.저녁,
        FatTaste.LOW_OILY,
        Taste.ADD_FLAVOR
    ),
    Meal(
        FoodName.회,
        //AreaDivision.JAPAN,
        FoodType.SASIMI,
        MainMealTime.저녁,
        FatTaste.LIGHT,
        Taste.TEXTURE
        //Taste.NONE
    ),
    Meal(
        FoodName.초밥,
        //AreaDivision.JAPAN,
        FoodType.RICE,
        MainMealTime.저녁,
        FatTaste.LIGHT,
        Taste.TEXTURE
        //Taste.SOY_SAUCE
    ),
    Meal(
        FoodName.감자탕,
        //AreaDivision.KOREA,
        FoodType.MEAT_SOUP,
        MainMealTime.저녁,
        FatTaste.LOW_OILY,
        Taste.SPICY
        //Taste.MEAT_SOUP
    ),
    Meal(
        FoodName.카레,
        //AreaDivision.ETC,
        FoodType.RICE,
        MainMealTime.점심,
        FatTaste.LIGHT,
        Taste.HOT
    ),
    Meal(
        FoodName.양념갈비,
        //AreaDivision.KOREA,
        FoodType.MEAT,
        MainMealTime.저녁,
        FatTaste.OILY,
        Taste.SWEETY_AND_SALTY
        //Taste.JUICE
    ),
    Meal(
        FoodName.간장찜닭,
        //AreaDivision.KOREA,
        FoodType.MEAT,
        MainMealTime.저녁,
        FatTaste.LOW_OILY,
        Taste.SWEETY_AND_SALTY
    ),
    Meal(
        FoodName.비빔밥,
        //AreaDivision.KOREA,
        FoodType.RICE,
        MainMealTime.점저,
        //Taste.RICE,
        FatTaste.MIDDLE,
        Taste.SPICY
    ),
    Meal(
        FoodName.피자,
        //AreaDivision.WEST,
        FoodType.BREAD,
        MainMealTime.저녁,
        FatTaste.OILY,
        Taste.SWEETY_AND_SALTY
    ),
    Meal(
        FoodName.국수,
        //AreaDivision.KOREA,
        FoodType.NOODLE,
        MainMealTime.점심,
        FatTaste.MIDDLE,
        Taste.SAVORY
    ),
    Meal(
        FoodName.칼국수,
        //AreaDivision.KOREA,
        FoodType.NOODLE,
        MainMealTime.점심,
        FatTaste.LIGHT,
        Taste.HITS_THE_SPOT
    ),
    Meal(
        FoodName.햄버거,
        //AreaDivision.WEST,
        FoodType.BREAD,
        MainMealTime.점심,
        FatTaste.OILY,
        Taste.SWEETY_AND_SALTY
        //Taste.JUICE
    ),
    Meal(
        FoodName.짬뽕,
        FoodType.NOODLE,
        MainMealTime.점심,
        FatTaste.MIDDLE,
        Taste.SPICY
    ),
    Meal(
        FoodName.짜장면,
        FoodType.NOODLE,
        MainMealTime.점심,
        FatTaste.LOW_OILY,
        Taste.SWEETY
    ),
    Meal(
        FoodName.라면,
        FoodType.NOODLE,
        MainMealTime.점심,
        FatTaste.MIDDLE,
        Taste.SPICY
    ),
    Meal(
        FoodName.토스트,
        FoodType.BREAD,
        MainMealTime.아침,
        FatTaste.MIDDLE,
        Taste.SAVORY
    ),
    Meal(
        FoodName.토마토_스파게티,
        FoodType.NOODLE,
        MainMealTime.점심,
        FatTaste.MIDDLE,
        Taste.SAVORY
    ),
    Meal(
        FoodName.우동,
        FoodType.NOODLE,
        MainMealTime.점심,
        FatTaste.LIGHT,
        Taste.SAVORY
    ),
    Meal(
        FoodName.탕수육,
        FoodType.MEAT,
        MainMealTime.저녁,
        FatTaste.LOW_OILY,
        Taste.TEXTURE
    ),
    Meal(
        FoodName.된장찌개,
        FoodType.RICE,
        MainMealTime.점저,
        FatTaste.LIGHT,
        Taste.SALTY
    ),
    Meal(
        FoodName.김치찌개,
        FoodType.RICE,
        MainMealTime.점저,
        FatTaste.MIDDLE,
        Taste.SPICY
    ),
    Meal(
        FoodName.제육덮밥,
        FoodType.RICE,
        MainMealTime.저녁,
        FatTaste.LOW_OILY,
        Taste.SPICY
    ),
    Meal(
        FoodName.고등어구이,
        FoodType.SEA_FOOD,
        MainMealTime.저녁,
        FatTaste.MIDDLE,
        Taste.TEXTURE
    ),
    Meal(
        FoodName.참치회,
        FoodType.SASIMI,
        MainMealTime.저녁,
        FatTaste.LOW_OILY,
        Taste.ADD_FLAVOR
    ),
    Meal(
        FoodName.낚지볶음,
        FoodType.SEA_FOOD,
        MainMealTime.저녁,
        FatTaste.MIDDLE,
        Taste.SPICY
    ),
    Meal(
        FoodName.갈치구이,
        FoodType.SEA_FOOD,
        MainMealTime.저녁,
        FatTaste.MIDDLE,
        Taste.TEXTURE
    ),
    Meal(
        FoodName.보쌈,
        FoodType.MEAT,
        MainMealTime.저녁,
        FatTaste.LOW_OILY,
        Taste.ADD_FLAVOR
    ),
    Meal(
        FoodName.아구찜,
        FoodType.SEA_FOOD,
        MainMealTime.저녁,
        FatTaste.LIGHT,
        Taste.SPICY
    ),
    Meal(
        FoodName.볶음밥,
        FoodType.RICE,
        MainMealTime.점심,
        FatTaste.LOW_OILY,
        Taste.SWEETY_AND_SALTY
    )
)