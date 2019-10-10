package com.king.corp.foodapp.views.interfaces

import com.king.corp.foodapp.models.Food

interface MainMenuView {
    fun showFoods(favoriteFoods: ArrayList<Food>, latestFoods: ArrayList<Food>)

    fun showProgress()

    fun hideProgress()
}