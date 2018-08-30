package devcamp.app.tokolelang.ui.product.create

import devcamp.app.tokolelang.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class ProductCreatePresenter(view: ProductCreateView): BasePresenter<ProductCreateView>() {

    init { super.attachView(view) }

    fun postProduct(name: String, productCondition: String, minPrice: String, nextBid: String,
                    expired: String, productCategory: String, userId: String, image: String) {
        view().showLoading()
        subscribe(routes.postProduct(name, productCondition, minPrice, nextBid,
                expired, productCategory, userId, image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { res ->
                            run {
                                if (res.code == 200) {
                                    view().onPostProduct(res)
                                    view().hideLoading()
                                } else {
                                    requestError(res.code, res.message)
                                }
                            }
                        },
                        { err -> requestError(err) }
                ))
    }

    fun onProductCategory() {
        view().showLoading()
        subscribe(routes.getProductCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { res ->
                            run {
                                if (res.code == 200) {
                                    view().onProductCategory(res)
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