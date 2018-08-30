package devcamp.app.tokolelang.ui.main

import android.support.v7.widget.GridLayoutManager
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.view.*

class MainActivity: BaseActivity<MainPresenter>(), MainView {

    override fun initPresenter(): MainPresenter = MainPresenter(this)
    override fun contentView(): Int = R.layout.activity_main

    override fun onCreated() {
        lstProducts.layoutManager = GridLayoutManager(this, 2)
        lstProducts.adapter = ProductAdapter(presenter.getProduct())
    }

    override fun success() {}

}
