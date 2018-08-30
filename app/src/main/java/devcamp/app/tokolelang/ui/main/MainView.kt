package devcamp.app.tokolelang.ui.main

import devcamp.app.tokolelang.base.BaseView
import devcamp.app.tokolelang.data.model.Product

/**
 * Created by isfaaghyth on 8/29/18.
 * github: @isfaaghyth
 */
interface MainView: BaseView {
    fun onGetProducts(products: List<Product>)
}