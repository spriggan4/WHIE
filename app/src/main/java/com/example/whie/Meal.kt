package com.example.whie

import android.os.Parcel
import android.os.Parcelable

enum class AreaDivision { KOREA, WEST, JAPAN, ETC }
enum class FoodType { MEAT, MEAT_SOUP, BREAD, SASIMI, NOODLE, RICE }
enum class MainMealTime { MORNING, LUNCH, DINNER, ANYTIME }
enum class Taste {
    OILY, LOW_OILY, SWEETY_AND_SALTY, TEXTURE,
    SAVORY, SALTY, SOUP, HITS_THE_SPOT, CHEESY, RICE, HOT, PEPPERY, ADD_FLAVOR,
    SOY_SAUCE
}

data class Meal(
    val foodName: String?,
    val areaDivision: Int,
    val foodType: Int,
    val mainMealTime: Int,
    val taste1: Int,
    val taste2: Int = 1000,
    val taste3: Int = 1000
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readInt(),
        source.readInt(),
        source.readInt(),
        source.readInt(),
        source.readInt(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(foodName)
        writeInt(areaDivision)
        writeInt(foodType)
        writeInt(mainMealTime)
        writeInt(taste1)
        writeInt(taste2)
        writeInt(taste3)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Meal> = object : Parcelable.Creator<Meal> {
            override fun createFromParcel(source: Parcel): Meal = Meal(source)
            override fun newArray(size: Int): Array<Meal?> = arrayOfNulls(size)
        }
    }
}