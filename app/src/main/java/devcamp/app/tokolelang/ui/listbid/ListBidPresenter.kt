package devcamp.app.tokolelang.ui.listbid

import devcamp.app.tokolelang.base.BasePresenter
import io.isfaaghyth.rak.Rak
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by isfaaghyth on 8/31/18.
 * github: @isfaaghyth
 */
class ListBidPresenter(view: ListBidView): BasePresenter<ListBidView>() {

    init { super.attachView(view) }

    fun getMyHistory() {
        val userId: Int = Rak.grab("userId")
        view().showLoading()
        subscribe(routes.getWinnerByUserId(userId.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res -> run {
                        view().hideLoading()
                        view().getHistory(res)
                    }
                }))
    }

}