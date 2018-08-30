package devcamp.app.tokolelang.ui.product.detail

import android.graphics.Color
import android.graphics.PorterDuff
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseActivity
import kotlinx.android.synthetic.main.activity_product_detail.*

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class ProductDetailActivity: BaseActivity<ProductDetailPresenter>(), ProductDetailView {

    override fun initPresenter(): ProductDetailPresenter = ProductDetailPresenter(this)
    override fun contentView(): Int = R.layout.activity_product_detail

    override fun onCreated() {
        setNavigationUp(toolbar, true)
        appBarLayout.addOnOffsetChangedListener { _, verticalOffset ->
            if (verticalOffset <= -imgProduct.height / 2) {
                toolbar.navigationIcon?.setColorFilter(Color.parseColor("#34495E"), PorterDuff.Mode.SRC_ATOP)
                txtTitle.setTextColor(Color.parseColor("#34495E"))
                txtTitle.text = "Product"
            } else {
                toolbar.navigationIcon?.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP)
                txtTitle.setTextColor(Color.parseColor("#FFFFFF"))
                txtTitle.text = ""
            }
        }
    }

}
