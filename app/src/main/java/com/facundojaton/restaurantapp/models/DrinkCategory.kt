package com.facundojaton.restaurantapp.models

import com.facundojaton.restaurantapp.utils.AppConstants
import java.io.Serializable


data class DrinkCategory(
    var name: String? = AppConstants.EMPTY_STRING
) : Serializable {

}