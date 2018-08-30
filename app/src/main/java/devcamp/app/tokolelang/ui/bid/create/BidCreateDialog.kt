package devcamp.app.tokolelang.ui.bid.create

import android.view.View
import com.github.rahatarmanahmed.cpv.CircularProgressView
import com.google.gson.Gson
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseDialog
import devcamp.app.tokolelang.data.model.Product
import devcamp.app.tokolelang.ui.product.detail.ProductDetailActivity
import devcamp.app.tokolelang.utils.RupiahConverter
import io.isfaaghyth.rak.Rak
import kotlinx.android.synthetic.main.dialog_bid_new.*
import android.os.Handler
import android.support.design.widget.BottomSheetBehavior
import android.widget.FrameLayout
import android.support.design.widget.BottomSheetDialog
import android.view.ViewTreeObserver



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
    private lateinit var userId: String

    override fun onCreated(view: View) {
        product = Gson().fromJson(arguments?.getString("data"), Product::class.java)
        userId = (Rak.grab("userId") as Int).toString()
        txtMinimumBid.text = RupiahConverter.convert((product.minPrice + product.nextBid).toDouble())
        onCloseDialogClicked()
        onCreateBidClicked()
    }

    private fun onCloseDialogClicked() = btnClose.setOnClickListener { dismiss() }

    private fun onCreateBidClicked() = btnBid.setOnClickListener {
        progressBar.visibility = View.VISIBLE
        Handler().postDelayed({ onBidSuccess() }, 3000)
    }

    override fun onBidSuccess() {
        (activity as ProductDetailActivity).getBidder()
        dismiss()
    }

}