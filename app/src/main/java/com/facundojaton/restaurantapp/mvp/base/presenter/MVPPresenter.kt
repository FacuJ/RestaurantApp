package com.autosolve.yasale.mvp.base.presenter

import com.autosolve.yasale.mvp.base.view.MVPView

interface MVPPresenter<V : MVPView> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}