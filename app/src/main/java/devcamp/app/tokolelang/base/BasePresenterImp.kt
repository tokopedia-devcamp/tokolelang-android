package devcamp.app.tokolelang.base

import io.reactivex.disposables.Disposable

/**
 * Created by isfaaghyth on 8/29/18.
 * github: @isfaaghyth
 */
interface BasePresenterImp<V> {
    fun subscribe(disposable: Disposable?)
    fun attachView(view: V)
    fun dettachView()
    fun view(): V
}