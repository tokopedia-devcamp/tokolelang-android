package devcamp.app.tokolelang.ui.main

import devcamp.app.tokolelang.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by isfaaghyth on 8/29/18.
 * github: @isfaaghyth
 */

class MainPresenter(view: MainView): BasePresenter<MainView>() {

    init { super.attachView(view) }

    fun getProducts() {
        view().showLoading()
        subscribe(routes.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { res ->
                            run {
                                if (res.code == 200) {
                                    view().onGetProducts(res.data)
                                    view().hideLoading()
                                } else {
                                    requestError(res.code, res.message)
                                }
                            }
                        },
                        { err -> requestError(err) }
                ))
    }

}