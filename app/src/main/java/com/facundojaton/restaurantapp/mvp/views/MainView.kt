package com.facundojaton.restaurantapp.mvp.views

import com.facundojaton.restaurantapp.models.TableOrder
import com.facundojaton.restaurantapp.mvp.base.view.MVPView

interface MainView : MVPView {
    fun onTableOrdersLoaded(tableOrders: ArrayList<TableOrder>)
    fun onEmptyTableOrders()
    fun onTableOrderAdded(tableOrder: TableOrder)
    fun onTableOrderRemoved(tableOrder: TableOrder)
    fun onTableOrderChanged(tableOrder: TableOrder)
    fun goToTableOrderActivity()
    fun setMainData()
}