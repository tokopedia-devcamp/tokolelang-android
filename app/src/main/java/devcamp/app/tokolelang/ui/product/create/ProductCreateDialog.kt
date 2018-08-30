package devcamp.app.tokolelang.ui.product.create

import android.view.View
import com.github.rahatarmanahmed.cpv.CircularProgressView
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseDialog
import kotlinx.android.synthetic.main.dialog_product_new.*

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class ProductCreateDialog : BaseDialog<ProductCreatePresenter>(), ProductCreateView {

    override fun initPresenter(): ProductCreatePresenter = ProductCreatePresenter(this)
    override fun contentView(): Int = R.layout.dialog_product_new
    override fun loader(): CircularProgressView = null!!

    fun newInstance(): ProductCreateDialog = ProductCreateDialog()

    override fun onCreated(view: View) {
        onCloseDialogClicked()
    }

    private fun onCloseDialogClicked() = btnClose.setOnClickListener { dismiss() }

}
