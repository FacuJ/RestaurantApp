package com.facundojaton.restaurantapp.models

import com.facundojaton.restaurantapp.utils.AppConstants
import java.io.Serializable

data class MealOrder(
    var name: String? = AppConstants.EMPTY_STRING,
    var category: String? = AppConstants.EMPTY_STRING,
    var mealId: String? = AppConstants.EMPTY_STRING,
    var price: String? = AppConstants.EMPTY_STRING,
    var state: String = AppConstants.EMPTY_STRING
) : Serializable {
    companion object {
        const val IN_PREPARATION = "IN_PREPARATION"
        const val READY = "READY"
    }

}