package devcamp.app.tokolelang.ui.product.detail

import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseActivity

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class ProductDetailActivity: BaseActivity<ProductDetailPresenter>(), ProductDetailView {

    override fun initPresenter(): ProductDetailPresenter = ProductDetailPresenter(this)
    override fun contentView(): Int = R.layout.activity_product_detail

    override fun onCreated() {

    }

}
