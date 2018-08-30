package devcamp.app.tokolelang.ui.product.create

import devcamp.app.tokolelang.base.BaseView
import devcamp.app.tokolelang.data.model.Category
import devcamp.app.tokolelang.data.model.DataRepository
import devcamp.app.tokolelang.data.model.Success

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
interface ProductCreateView: BaseView {
    fun onProductCategory(categories: DataRepository<Category>)
    fun onPostProduct(result: Success)
}