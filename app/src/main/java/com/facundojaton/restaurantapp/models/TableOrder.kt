package com.facundojaton.restaurantapp.models

import com.facundojaton.restaurantapp.controllers.UserController
import com.facundojaton.restaurantapp.utils.AppConstants
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class TableOrder(
    var id: String? = AppConstants.EMPTY_STRING,
    var state: String? = AppConstants.EMPTY_STRING,
    var drinkOrders: ArrayList<DrinkOrder> = ArrayList(),
    var mealOrders: ArrayList<MealOrder> = ArrayList(),
    var tableNumber: String? = AppConstants.EMPTY_STRING,
    var updateDate: String? = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        .format(Calendar.getInstance().time),
    var workingDayDate: String? = UserController.workingDayConfigs,
    var waiterId: String? = AppConstants.EMPTY_STRING,
    var waiterName: String? = AppConstants.EMPTY_STRING
    ) : Serializable {
    object TableOrderState {
        const val NEW = "NEW"
        const val RECEIVED = "RECEIVED"
        const val READY = "READY"
    }

    companion object {
        const val WAITER_ID = "waiterId"
        const val TABLE_USED = "TABLE_USED"
        const val TABLE_ORDER_CREATED_SUCCESSFULLY = "TABLE_ORDER_CREATED_SUCCESSFULLY"
        const val TABLE_NUMBER_NULL = "TABLE_NUMBER_NULL"
        const val TABLE_ORDER_EDITED_SUCCESSFULLY = "TABLE_ORDER_EDITED_SUCCESSFULLY"
        const val TABLE_ID_NULL = "TABLE_ID_NULL"
        const val TABLE_ORDER_NOT_FOUND = "TABLE_ORDER_NOT_FOUND"
    }
}
