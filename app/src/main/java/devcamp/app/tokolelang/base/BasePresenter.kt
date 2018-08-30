package devcamp.app.tokolelang.base

import android.util.Log
import devcamp.app.tokolelang.network.Network
import devcamp.app.tokolelang.network.Routes
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by isfaaghyth on 8/29/18.
 * github: @isfaaghyth
 */
open class BasePresenter<V: BaseView> : BasePresenterImp<V> {

    private lateinit var mvpView : V
    private lateinit var composite : CompositeDisposable
    protected lateinit var routes : Routes

    override fun attachView(view: V) {
        mvpView = view
        routes = Network.create()
    }

    override fun subscribe(disposable: Disposable?) {
        composite = CompositeDisposable()
        composite.add(disposable)
    }

    override fun dettachView() {
        if (composite.isDisposed) composite.clear()
    }

    fun requestError(error: Throwable?) {
        view().hideLoading()
        Log.d("TAG", error?.message)
    }

    override fun view(): V = mvpView

}