package devcamp.app.tokolelang.ui.main

import android.support.v7.widget.GridLayoutManager
import com.github.rahatarmanahmed.cpv.CircularProgressView
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseActivity
import devcamp.app.tokolelang.data.model.Product
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.view.*

class MainActivity: BaseActivity<MainPresenter>(), MainView {

    override fun initPresenter(): MainPresenter = MainPresenter(this)
    override fun contentView(): Int = R.layout.activity_main
    override fun loader(): CircularProgressView = header.progressBar

    override fun onCreated() {
        lstProducts.layoutManager = GridLayoutManager(this, 2)
        presenter.getProducts()
    }

    override fun onGetProducts(products: List<Product>) {
        lstProducts.adapter = ProductAdapter(products)
    }

}
