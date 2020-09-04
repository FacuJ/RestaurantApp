package com.facundojaton.restaurantapp.models

import com.facundojaton.restaurantapp.utils.AppConstants
import java.io.Serializable


data class Table(
    var enabled: Boolean? = true,
    var name: String? = AppConstants.EMPTY_STRING
) : Serializable