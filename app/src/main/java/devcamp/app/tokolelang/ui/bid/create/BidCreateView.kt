package devcamp.app.tokolelang.ui.bid.create

import devcamp.app.tokolelang.base.BaseView
import devcamp.app.tokolelang.data.model.Success

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
interface BidCreateView: BaseView {
    fun onBidSuccess(result: Success)
}