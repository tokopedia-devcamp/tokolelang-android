package devcamp.app.tokolelang.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.github.rahatarmanahmed.cpv.CircularProgressView
import com.google.gson.Gson
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseActivity
import devcamp.app.tokolelang.data.model.Product
import devcamp.app.tokolelang.ui.login.LoginActivity
import devcamp.app.tokolelang.ui.product.create.ProductCreateDialog
import io.isfaaghyth.rak.Rak
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.view.*

class MainActivity: BaseActivity<MainPresenter>(), MainView {

    override fun initPresenter(): MainPresenter = MainPresenter(this)
    override fun contentView(): Int = R.layout.activity_main
    override fun loader(): CircularProgressView = header.progressBar

    override fun onCreated() {
        setSupportActionBar(header.toolbar)
        lstProducts.layoutManager = GridLayoutManager(this, 2)
        presenter.getProducts()

        btnAddProduct.setOnClickListener {
            val args = Bundle()
            val createBid = ProductCreateDialog().newInstance()
            args.putString("data", "")
            createBid.arguments = args
            createBid.show(supportFragmentManager, "Create")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onGetProducts(products: List<Product>) {
        lstProducts.adapter = ProductAdapter(products)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.mn_refresh -> {
                presenter.getProducts()
            }
            R.id.mn_logout -> {
                Rak.entry("login", false)
                Rak.removeAll(this)
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
