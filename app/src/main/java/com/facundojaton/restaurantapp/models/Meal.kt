package com.facundojaton.restaurantapp.models

import com.facundojaton.restaurantapp.utils.AppConstants
import java.io.Serializable

data class Meal(
    var category: String? = AppConstants.EMPTY_STRING,
    var enabled: Boolean? = true,
    var id: String? = AppConstants.EMPTY_STRING,
    var name:String? = AppConstants.EMPTY_STRING,
    var price:String? = AppConstants.EMPTY_STRING
):Serializable {
    companion object {
        const val MEAL_ID = "id"
    }

}