package devcamp.app.tokolelang.ui.product.detail

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import com.bumptech.glide.Glide
import com.github.rahatarmanahmed.cpv.CircularProgressView
import com.google.gson.Gson
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseActivity
import devcamp.app.tokolelang.data.model.Product
import devcamp.app.tokolelang.utils.ExpiredView
import devcamp.app.tokolelang.utils.RemainingDays
import devcamp.app.tokolelang.utils.RupiahConverter
import kotlinx.android.synthetic.main.activity_product_detail.*
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.request.target.BitmapImageViewTarget
import devcamp.app.tokolelang.ui.bid.create.BidCreateDialog
import android.R.attr.resource
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v4.graphics.drawable.RoundedBitmapDrawable




/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class ProductDetailActivity: BaseActivity<ProductDetailPresenter>(), ProductDetailView {

    override fun initPresenter(): ProductDetailPresenter = ProductDetailPresenter(this)
    override fun contentView(): Int = R.layout.activity_product_detail
    override fun loader(): CircularProgressView = progressBar

    private lateinit var product: Product

    override fun onCreated() {
        product = Gson().fromJson(intent.getStringExtra("data"), Product::class.java)
        showProductDetail(product)
        setupAppBar()
    }

    fun getBidder() {
        Log.e("TAG", "REFRESH")
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

        txtHighestBid.text = RupiahConverter.convert(product.totalBidder.toDouble())
        txtNextBid.text = RupiahConverter.convert(product.nextBid.toDouble())
        txtMinPrice.text = RupiahConverter.convert(product.minPrice.toDouble())
    }

    private fun makeBidClicked(product: Product) = btnMakeBid.setOnClickListener {
        val args = Bundle()
        val createBid = BidCreateDialog().newInstance()
        args.putString("data", Gson().toJson(product))
        createBid.arguments = args
        createBid.show(supportFragmentManager, "Detail")
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
