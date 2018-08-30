package devcamp.app.tokolelang.ui.main

import android.util.Log
import devcamp.app.tokolelang.base.BasePresenter

/**
 * Created by isfaaghyth on 8/29/18.
 * github: @isfaaghyth
 */

class MainPresenter(view: MainView): BasePresenter<MainView>() {

    init {
        super.attachView(view)
    }

    fun getProduct() {
        view().success()
    }

}