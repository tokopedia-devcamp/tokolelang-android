package devcamp.app.tokolelang.ui.main

import android.os.Bundle
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity<MainPresenter>(), MainView {

    override fun initPresenter(): MainPresenter = MainPresenter(this)
    override fun contentView(): Int = R.layout.activity_main

    override fun onCreated() {
        test.text = "hahaha"
        presenter.getProduct()
    }

    override fun success() {
        onInfo("yey!")
    }

}
