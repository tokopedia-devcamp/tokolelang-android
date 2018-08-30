package devcamp.app.tokolelang.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.github.rahatarmanahmed.cpv.CircularProgressView
import com.google.gson.Gson
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseActivity
import devcamp.app.tokolelang.data.model.Product
import devcamp.app.tokolelang.ui.listbid.ListBidActivity
import devcamp.app.tokolelang.ui.login.LoginActivity
import devcamp.app.tokolelang.ui.product.create.ProductCreateDialog
import devcamp.app.tokolelang.utils.StartSnapHelper
import io.isfaaghyth.rak.Rak
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.view.*
import java.util.ArrayList

class MainActivity: BaseActivity<MainPresenter>(), MainView {

    override fun initPresenter(): MainPresenter = MainPresenter(this)
    override fun contentView(): Int = R.layout.activity_main
    override fun loader(): CircularProgressView = header.progressBar

    override fun onCreated() {
        setSupportActionBar(header.toolbar)
        lstProducts.layoutManager = GridLayoutManager(this, 2)
        lstProducts.isNestedScrollingEnabled = false
        presenter.getProducts()

        btnAddProduct.setOnClickListener {
            val args = Bundle()
            val createBid = ProductCreateDialog().newInstance()
            args.putString("data", "")
            createBid.arguments = args
            createBid.show(supportFragmentManager, "Create")
        }
    }

    fun requestTopBid(products: List<Product>) {
        val highLightMovies = ArrayList<Product>()
        highLightMovies.add(products.get(0))
        highLightMovies.add(products.get(1))
        highLightMovies.add(products.get(2))
        lstHighlight.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = StartSnapHelper()
        snapHelper.attachToRecyclerView(lstHighlight)
        val highlightAdapter = TopProductAdapter(highLightMovies)
        lstHighlight.adapter = highlightAdapter
    }

    fun refreshProductsList() {
        presenter.getProducts()
    }

    override fun onResume() {
        super.onResume()
        refreshProductsList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onGetProducts(products: List<Product>) {
        lstProducts.adapter = ProductAdapter(products)
        requestTopBid(products)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.mn_history -> {
                startActivity(Intent(this, ListBidActivity::class.java))
            }
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
