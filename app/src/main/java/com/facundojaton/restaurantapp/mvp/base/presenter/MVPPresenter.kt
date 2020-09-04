package com.facundojaton.restaurantapp.mvp.base.presenter

import com.facundojaton.restaurantapp.mvp.base.view.MVPView


interface MVPPresenter<V : MVPView> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}