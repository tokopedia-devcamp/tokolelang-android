package devcamp.app.tokolelang.ui.product.detail

import devcamp.app.tokolelang.base.BaseView
import devcamp.app.tokolelang.data.model.Bidder
import devcamp.app.tokolelang.data.model.DataRepository
import devcamp.app.tokolelang.data.model.Success

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
interface ProductDetailView: BaseView {
    fun onGetBidders(result: DataRepository<Bidder>)
    fun postWinnerSuccess(result: Success)
}