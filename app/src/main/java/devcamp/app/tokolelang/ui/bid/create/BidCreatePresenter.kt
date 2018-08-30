package devcamp.app.tokolelang.ui.bid.create

import devcamp.app.tokolelang.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class BidCreatePresenter(view: BidCreateView): BasePresenter<BidCreateView>() {

    init { super.attachView(view) }

    fun postBid(userId: String, productId: String, price: String) {
        view().showLoading()
        subscribe(routes.postBidProject(userId, productId, price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res -> run {
                        view().onBidSuccess(res)
                        view().hideLoading()
                    }
                }))
    }

}