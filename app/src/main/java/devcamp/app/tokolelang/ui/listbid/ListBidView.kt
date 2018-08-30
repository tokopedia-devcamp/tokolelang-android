package devcamp.app.tokolelang.ui.listbid

import devcamp.app.tokolelang.base.BaseView
import devcamp.app.tokolelang.data.model.DataRepository
import devcamp.app.tokolelang.data.model.Winner

/**
 * Created by isfaaghyth on 8/31/18.
 * github: @isfaaghyth
 */
interface ListBidView: BaseView {
    fun getHistory(result: DataRepository<Winner>)
}