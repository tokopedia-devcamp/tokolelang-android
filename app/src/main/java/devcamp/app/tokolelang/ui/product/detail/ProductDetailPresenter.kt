package devcamp.app.tokolelang.ui.product.detail

import devcamp.app.tokolelang.base.BasePresenter
import devcamp.app.tokolelang.data.model.Bidder

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class ProductDetailPresenter(view: ProductDetailView): BasePresenter<ProductDetailView>() {

    init { super.attachView(view) }

    fun getBidders(): List<Bidder> {
        val products = arrayListOf<Bidder>()
        products.add(Bidder("Hasby", "Rp 4566"))
        products.add(Bidder("Sebastian", "Rp 234234"))
        products.add(Bidder("Isfa", "Rp 345453"))
        return products
    }

}