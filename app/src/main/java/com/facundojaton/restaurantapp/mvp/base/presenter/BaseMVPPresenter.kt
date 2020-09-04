package com.facundojaton.restaurantapp.mvp.base.presenter

import com.facundojaton.restaurantapp.mvp.base.view.MVPView

abstract class BaseMVPPresenter<V : MVPView> :
    MVPPresenter<V> {

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        view = null
    }

}