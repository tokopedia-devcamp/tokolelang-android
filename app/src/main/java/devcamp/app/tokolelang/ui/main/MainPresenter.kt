package devcamp.app.tokolelang.ui.main

import devcamp.app.tokolelang.base.BasePresenter
import devcamp.app.tokolelang.data.model.Product

/**
 * Created by isfaaghyth on 8/29/18.
 * github: @isfaaghyth
 */

class MainPresenter(view: MainView): BasePresenter<MainView>() {

    init { super.attachView(view) }

    fun getProduct(): List<Product> {
        val products = arrayListOf<Product>()
        products.add(Product("Uang", "Rp 4566", "1", "456"))
        products.add(Product("Kartu", "Rp 234234", "0", "321"))
        products.add(Product("Kasur", "Rp 345453", "3", "123"))
        products.add(Product("Kertas", "Rp 34535", "2", "123"))
        products.add(Product("Karet Gelang Ajaib", "Rp 124124", "3", "123"))
        products.add(Product("Kulit Kerang", "Rp 124124", "1", "123"))
        return products
    }

}