package devcamp.app.tokolelang.ui.bid.create

import android.text.Editable
import android.text.TextWatcher
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
        edtAmountBid.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) =
                    edtAmountBid.setText("Rp. ${edtAmountBid.text}")

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        onCloseDialogClicked()
        onCreateBidClicked()
    }

    private fun onCloseDialogClicked() = btnClose.setOnClickListener { dismiss() }

    private fun onCreateBidClicked() = btnBid.setOnClickListener {
        progressBar.visibility = View.VISIBLE
    }

}