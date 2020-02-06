package com.example.whie

import android.os.Parcel
import android.os.Parcelable

//enum class AreaDivision { KOREA, WEST, JAPAN, ETC }
enum class FoodType { MEAT, MEAT_SOUP, BREAD, SASIMI, NOODLE, RICE }
enum class MainMealTime { 아침, 점심, 저녁, 점저 }
enum class FatTaste {
    OILY, LOW_OILY, MIDDLE, LIGHT
}
enum class Taste {
    SWEETY_AND_SALTY,
    SAVORY, SALTY, SWEETY, HOT, SPICY, HITS_THE_SPOT,TEXTURE
}

data class Meal(
    val foodName: String?,
    //val areaDivision: AreaDivision,
    val foodType: FoodType,
    val mainMealTime: MainMealTime,
    val fatTaste: FatTaste,
    val taste: Taste
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
       //AreaDivision.values()[source.readInt()],
        FoodType.values()[source.readInt()],
        MainMealTime.values()[source.readInt()],
        FatTaste.values()[source.readInt()],
        Taste.values()[source.readInt()]
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(foodName)
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

val meals = arrayOf(
    Meal(
        "삼겹살",
        //AreaDivision.KOREA,
        FoodType.MEAT,
        MainMealTime.저녁,
        FatTaste.OILY,
        Taste.TEXTURE
    ),
    Meal(
        "오리고기",
        //AreaDivision.KOREA,
        FoodType.MEAT,
        MainMealTime.저녁,
        FatTaste.LOW_OILY,
        Taste.TEXTURE
    ),
    Meal(
        "회",
        //AreaDivision.JAPAN,
        FoodType.SASIMI,
        MainMealTime.저녁,
        FatTaste.LIGHT,
        Taste.TEXTURE
        //Taste.NONE
    ),
    Meal(
        "초밥",
        //AreaDivision.JAPAN,
        FoodType.RICE,
        MainMealTime.저녁,
        FatTaste.LIGHT,
        Taste.TEXTURE
        //Taste.SOY_SAUCE
    ),
    Meal(
        "감자탕",
        //AreaDivision.KOREA,
        FoodType.MEAT_SOUP,
        MainMealTime.저녁,
        FatTaste.LOW_OILY,
        Taste.SPICY
        //Taste.MEAT_SOUP
    ),
    Meal(
        "카레",
        //AreaDivision.ETC,
        FoodType.RICE,
        MainMealTime.점심,
        FatTaste.LIGHT,
        Taste.HOT
    ),
    Meal(
        "돼지갈비",
        //AreaDivision.KOREA,
        FoodType.MEAT,
        MainMealTime.저녁,
        FatTaste.OILY,
        Taste.SWEETY_AND_SALTY
        //Taste.JUICE
    ),
    Meal(
        "간장찜닭",
        //AreaDivision.KOREA,
        FoodType.MEAT,
        MainMealTime.저녁,
        FatTaste.LOW_OILY,
        Taste.SWEETY_AND_SALTY
    ),
    Meal(
        "비빔밥",
        //AreaDivision.KOREA,
        FoodType.RICE,
        MainMealTime.점저,
        //Taste.RICE,
        FatTaste.MIDDLE,
        Taste.SPICY
    ),
    Meal(
        "피자",
        //AreaDivision.WEST,
        FoodType.BREAD,
        MainMealTime.저녁,
        FatTaste.OILY,
        Taste.SWEETY_AND_SALTY
    ),
    Meal(
        "국수",
        //AreaDivision.KOREA,
        FoodType.NOODLE,
        MainMealTime.점심,
        FatTaste.MIDDLE,
        Taste.SAVORY
    ),
    Meal(
        "칼국수",
        //AreaDivision.KOREA,
        FoodType.NOODLE,
        MainMealTime.점심,
        FatTaste.LIGHT,
        Taste.HITS_THE_SPOT
    ),
    Meal(
        "햄버거",
        //AreaDivision.WEST,
        FoodType.BREAD,
        MainMealTime.점심,
        FatTaste.OILY,
        Taste.SWEETY_AND_SALTY
        //Taste.JUICE
    )
)