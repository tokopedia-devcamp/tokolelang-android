package devcamp.app.tokolelang.ui.product.detail

import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import com.bumptech.glide.Glide
import com.github.rahatarmanahmed.cpv.CircularProgressView
import com.google.gson.Gson
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseActivity
import devcamp.app.tokolelang.utils.ExpiredView
import devcamp.app.tokolelang.utils.RemainingDays
import devcamp.app.tokolelang.utils.RupiahConverter
import kotlinx.android.synthetic.main.activity_product_detail.*
import android.os.Bundle
import com.bumptech.glide.request.target.BitmapImageViewTarget
import devcamp.app.tokolelang.ui.bid.create.BidCreateDialog
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import devcamp.app.tokolelang.data.model.*
import io.isfaaghyth.rak.Rak
import kotlinx.android.synthetic.main.layout_message.*


/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class ProductDetailActivity: BaseActivity<ProductDetailPresenter>(), ProductDetailView {

    override fun initPresenter(): ProductDetailPresenter = ProductDetailPresenter(this)
    override fun contentView(): Int = R.layout.activity_product_detail
    override fun loader(): CircularProgressView = progressBar

    private lateinit var product: Product
    private lateinit var bidder: BidderUser

    override fun onCreated() {
        btnRefreshBidderList.setOnClickListener { getBidder() }
        product = Gson().fromJson(intent.getStringExtra("data"), Product::class.java)
        isMyBid()
        lstBiddger.layoutManager = LinearLayoutManager(this)
        getBidder()
        showProductDetail(product)
        setupAppBar()
    }

    fun getBidder() = presenter.getBidders(product.productId)

    override fun onGetBidders(result: DataRepository<Bidder>) {
        when(result.code) {
            200 -> {
                txtHighestBid.text = RupiahConverter.convert(result.data[0].price.toDouble())
                lstBiddger.adapter = BidderAdapter(result.data)
                bidder = result.data[0].bidder
                txtBidderCount.text = getString(R.string.bidder)
            }
            400 -> {
                txtHighestBid.text = RupiahConverter.convert(0.toDouble())
                txtBidderCount.text = getString(R.string.bidder_not_found)
            }
        }
    }

    private fun showProductDetail(product: Product) {
        makeBidClicked(product)

        txtProductName.text = product.name
        ExpiredView.expiredTime(this, txtExpired, RemainingDays.calculateFromNow(product.expired))
        txtPersonTotal.text = product.totalBidder.toString()

        Glide.with(this)
                .load(product.imageurl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imgProduct)

        Glide.with(this)
                .load(product.seller.avatar)
                .asBitmap()
                .centerCrop()
                .into(object : BitmapImageViewTarget(imgAvatar) {
                    override fun setResource(resource: Bitmap?) {
                        val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, resource)
                        circularBitmapDrawable.isCircular = true
                        imgAvatar.setImageDrawable(circularBitmapDrawable)
                    }
                })

        txtNextBid.text = RupiahConverter.convert(product.nextBid.toDouble())
        txtMinPrice.text = RupiahConverter.convert(product.minPrice.toDouble())
    }

    private fun makeBidClicked(product: Product) = btnBid.setOnClickListener {
        val userId: Int = Rak.grab("userId")
        if (product.seller.id == userId) {
            cancelMyBid()
        } else {
            val args = Bundle()
            val createBid = BidCreateDialog().newInstance()
            args.putString("data", Gson().toJson(product))
            createBid.arguments = args
            createBid.show(supportFragmentManager, "Detail")
        }
    }

    private fun isMyBid() {
        val userId: Int = Rak.grab("userId")
        if (product.seller.id == userId) {
            btnBid.setBackgroundColor(Color.parseColor("#FFB236"))
            btnBid.text = getString(R.string.cancel_bid)
        } else {
            btnBid.setBackgroundColor(Color.parseColor("#2ECC71"))
            btnBid.text = getString(R.string.make_bid)
        }
    }

    private fun cancelMyBid() {
        AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage(getString(R.string.are_you_sure_bid))
                .setNegativeButton("NO", { _, _ -> run {

                } })
                .setPositiveButton("YES", { _, _ -> run {
                    val userId: Int = Rak.grab("userId")
                    val finalPrice = txtHighestBid.text.toString()
                    presenter.postWinners(
                            bidder.userId.toString(),
                            product.productId,
                            "Selamat, anda menjadi top bid pada product ${product.name} dengan harga $finalPrice",
                            finalPrice.replace("Rp.", "").replace(",","").trim())
                } })
                .show()
    }

    override fun postWinnerSuccess(result: Success) {
        onBackPressed()
        finish()
    }

    private fun setupAppBar() {
        setNavigationUp(toolbar, true)
        appBarLayout.addOnOffsetChangedListener { _, verticalOffset ->
            if (verticalOffset <= -imgProduct.height / 2) {
                toolbar.navigationIcon?.setColorFilter(Color.parseColor("#34495E"), PorterDuff.Mode.SRC_ATOP)
                txtTitle.setTextColor(Color.parseColor("#34495E"))
                txtTitle.text = product.name
            } else {
                toolbar.navigationIcon?.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP)
                txtTitle.setTextColor(Color.parseColor("#FFFFFF"))
                txtTitle.text = ""
            }
        }
    }

}
