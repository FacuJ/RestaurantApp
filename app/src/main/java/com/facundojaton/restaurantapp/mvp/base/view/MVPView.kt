package com.facundojaton.restaurantapp.mvp.base.view


interface MVPView {
    fun showMessage(message: String)
    fun showProgressBar()
    fun hideProgressBar()
}