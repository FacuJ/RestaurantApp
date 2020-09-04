package com.facundojaton.restaurantapp.mvp.views

import com.autosolve.yasale.models.TableOrder
import com.autosolve.yasale.mvp.base.view.MVPView
import com.facundojaton.restaurantapp.models.TableOrder

interface MainView : MVPView {
    fun onTableOrdersLoaded(tableOrders: ArrayList<TableOrder>)
    fun onEmptyTableOrders()
    fun onTableOrderAdded(tableOrder: TableOrder)
    fun onTableOrderRemoved(tableOrder: TableOrder)
    fun onTableOrderChanged(tableOrder: TableOrder)
    fun goToTableOrderActivity()
    fun setMainData()
}