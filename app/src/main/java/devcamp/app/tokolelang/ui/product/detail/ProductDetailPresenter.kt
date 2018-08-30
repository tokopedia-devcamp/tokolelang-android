package devcamp.app.tokolelang.ui.product.detail

import devcamp.app.tokolelang.base.BasePresenter
import devcamp.app.tokolelang.data.model.Bidder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class ProductDetailPresenter(view: ProductDetailView): BasePresenter<ProductDetailView>() {

    init { super.attachView(view) }

    fun getBidders(productId: String) {
        view().showLoading()
        subscribe(routes.getBidderByProductId(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res -> run {
                        view().hideLoading()
                        view().onGetBidders(res)
                    }
                }))
    }

}