package com.facundojaton.restaurantapp.mvp.presenters

import android.content.Context
import com.facundojaton.restaurantapp.R
import com.facundojaton.restaurantapp.models.TableOrder
import com.facundojaton.restaurantapp.mvp.base.presenter.BaseMVPPresenter
import com.facundojaton.restaurantapp.mvp.views.MainView

class MainPresenter<V : MainView>(context: Context) : BaseMVPPresenter<V>() {
    private var context: Context? = null

    init {
        this.context = context
    }

    fun getTableOrders(id: String) {

    }

    fun firebaseLogout(userId: String) {
        }

    fun makeTableOrderHistorical(tableOrder: TableOrder) {

    }


    private fun onDefaultError() {
        context?.getString(R.string.default_error)?.let {
            getView()?.showMessage(it)
        } ?: kotlin.run {
            getView()?.showMessage("ERROR")
        }
    }
}