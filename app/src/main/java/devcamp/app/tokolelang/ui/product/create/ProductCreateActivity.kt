package devcamp.app.tokolelang.ui.product.create

import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseActivity

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class ProductCreateActivity: BaseActivity<ProductCreatePresenter>(), ProductCreateView {

    override fun initPresenter(): ProductCreatePresenter = ProductCreatePresenter(this)
    override fun contentView(): Int = R.layout.activity_product_new

    override fun onCreated() {

    }

}
