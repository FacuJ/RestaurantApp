package com.facundojaton.restaurantapp.models

import com.facundojaton.restaurantapp.utils.AppConstants
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

data class User(
    var id: String? = AppConstants.EMPTY_STRING,
    var token: String? = AppConstants.EMPTY_STRING,
    var userType: String? = UserType.WAITER,
    var fullName: String? = AppConstants.EMPTY_STRING,
    var enabled: Boolean? = true,
    var registerDate: String? = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(
        Calendar.getInstance().time
    )
    ) : Serializable {
    object UserType {
        internal const val WAITER = "WAITER"
        internal const val KITCHEN = "KITCHEN"
        internal const val ADMIN = "ADMIN"
    }

    companion object {
        const val TOKEN = "token"
        const val USER_TYPE = "userType"
    }
}