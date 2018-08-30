package devcamp.app.tokolelang.ui.bid.create

import android.util.Log
import android.view.View
import com.github.rahatarmanahmed.cpv.CircularProgressView
import com.google.gson.Gson
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseDialog
import devcamp.app.tokolelang.data.model.Product
import devcamp.app.tokolelang.utils.RupiahConverter
import kotlinx.android.synthetic.main.dialog_bid_new.*

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class BidCreateDialog : BaseDialog<BidCreatePresenter>(), BidCreateView {

    override fun initPresenter(): BidCreatePresenter = BidCreatePresenter(this)
    override fun contentView(): Int = R.layout.dialog_bid_new
    override fun loader(): CircularProgressView = progressBar

    fun newInstance(): BidCreateDialog = BidCreateDialog()

    private lateinit var product: Product

    override fun onCreated() {
        product = Gson().fromJson(arguments?.getString("data"), Product::class.java)
        txtMinimumBid.text = RupiahConverter.convert((product.minPrice + product.nextBid).toDouble())
        onCloseDialogClicked()
        onCreateBidClicked()
    }

    private fun onCloseDialogClicked() = btnClose.setOnClickListener { dismiss() }

    private fun onCreateBidClicked() = btnBid.setOnClickListener {
        progressBar.visibility = View.VISIBLE
    }

}