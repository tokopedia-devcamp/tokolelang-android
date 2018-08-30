package devcamp.app.tokolelang.ui.listbid

import android.support.v7.widget.LinearLayoutManager
import com.github.rahatarmanahmed.cpv.CircularProgressView
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseActivity
import devcamp.app.tokolelang.data.model.DataRepository
import devcamp.app.tokolelang.data.model.Winner
import kotlinx.android.synthetic.main.activity_bid_list.*
import kotlinx.android.synthetic.main.toolbar.view.*

/**
 * Created by isfaaghyth on 8/31/18.
 * github: @isfaaghyth
 */
class ListBidActivity: BaseActivity<ListBidPresenter>(), ListBidView {

    override fun initPresenter(): ListBidPresenter = ListBidPresenter(this)
    override fun contentView(): Int = R.layout.activity_bid_list
    override fun loader(): CircularProgressView = header.progressBar

    override fun onCreated() {
        setNavigationUp(header.toolbar, true)
        header.txtTitle.text = getString(R.string.list_bid)
        lstBid.layoutManager = LinearLayoutManager(this)
        presenter.getMyHistory()
    }

    override fun getHistory(result: DataRepository<Winner>) {
        if (result.data.isNotEmpty()) {
            lstBid.adapter = ListBidAdapter(result.data)
        } else {
            onError("Data not found")
        }
    }

}