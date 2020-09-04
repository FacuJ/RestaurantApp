package com.facundojaton.restaurantapp.models

import com.facundojaton.restaurantapp.utils.AppConstants
import java.io.Serializable

data class DrinkOrder(
    var name: String? = AppConstants.EMPTY_STRING,
    var category: String? = AppConstants.EMPTY_STRING,
    var drinkId: String? = AppConstants.EMPTY_STRING,
    var price: String? = AppConstants.EMPTY_STRING,
    var amount: Int = 0
) : Serializable