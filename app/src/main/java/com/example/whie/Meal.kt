package com.example.whie

import android.os.Parcel
import android.os.Parcelable

data class Meal(
    val foodName: String?,
    val areaDivision: String?,
    val foodType: String?,
    val mainMealTime: String?,
    val taste1: String?,
    var taste2: String? = "",
    var taste3: String? = ""
) : Parcelable {
    constructor(source: Parcel) : this(
        foodName = source.readString(),
        areaDivision = source.readString(),
        foodType = source.readString(),
        mainMealTime = source.readString(),
        taste1 = source.readString(),
        taste2 = source.readString(),
        taste3 = source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(foodName)
        writeString(areaDivision)
        writeString(foodType)
        writeString(mainMealTime)
        writeString(taste1)
        writeString(taste2)
        writeString(taste3)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Meal> = object : Parcelable.Creator<Meal> {
            override fun createFromParcel(source: Parcel): Meal = Meal(source)
            override fun newArray(size: Int): Array<Meal?> = arrayOfNulls(size)
        }
    }
}