package com.facundojaton.restaurantapp.controllers

import com.facundojaton.restaurantapp.models.*

object UserController {
    private var TAG = UserController::class.java.simpleName

    var currentUser: User? = null
    var drinks: ArrayList<Drink>? = null
    var meals: ArrayList<Meal>? = null
    var drinkCategories: ArrayList<DrinkCategory>? = null
    var mealCategories: ArrayList<MealCategory>? = null
    var tables: ArrayList<Table>? = null
    var workingDayConfigs: String? = null
}