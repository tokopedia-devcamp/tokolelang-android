package devcamp.app.tokolelang.ui.product.create

import android.app.AlertDialog
import android.view.View
import com.github.rahatarmanahmed.cpv.CircularProgressView
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseDialog
import kotlinx.android.synthetic.main.dialog_product_new.*
import android.graphics.Bitmap
import android.util.Base64
import android.widget.AdapterView
import com.bumptech.glide.Glide
import java.io.ByteArrayOutputStream
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.mlsdev.rximagepicker.RxImageConverters
import com.mlsdev.rximagepicker.Sources
import com.mlsdev.rximagepicker.RxImagePicker
import devcamp.app.tokolelang.data.model.Category
import devcamp.app.tokolelang.data.model.DataRepository
import devcamp.app.tokolelang.data.model.Success
import devcamp.app.tokolelang.ui.main.MainActivity
import devcamp.app.tokolelang.utils.DatePickerBuilder
import io.isfaaghyth.rak.Rak
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
class ProductCreateDialog : BaseDialog<ProductCreatePresenter>(), ProductCreateView {

    override fun initPresenter(): ProductCreatePresenter = ProductCreatePresenter(this)
    override fun contentView(): Int = R.layout.dialog_product_new
    override fun loader(): CircularProgressView = progressBar

    fun newInstance(): ProductCreateDialog = ProductCreateDialog()

    private lateinit var productImage: String
    private var productCondition: Int = 0
    private var productCategory: Int = 0

    override fun onCreated(view: View) {
        onCloseDialogClicked()
        imgProduct.setOnClickListener{ chooseImage() }
        edtExpired.setOnClickListener {
            DatePickerBuilder.show(context, object : DatePickerBuilder.ResultDateListener {
                override fun result(res: String) = edtExpired.setText(res)
            })
        }
        btnAddProduct.setOnClickListener {
            val userId: Int = Rak.grab("userId")
            presenter.postProduct(
                    edtProductName.text.toString(),
                    productCondition.toString(),
                    edtMinPrice.text.toString(),
                    edtNextBid.text.toString(),
                    edtExpired.text.toString(),
                    productCategory.toString(),
                    userId.toString(),
                    productImage
            )
        }
        presenter.onProductCategory()
        productConditionSpinner()

    }

    override fun onPostProduct(result: Success) {
        (activity as MainActivity).refreshProductsList()
        onInfo("Success add a new product!")
        dismiss()
    }

    override fun onProductCategory(categories: DataRepository<Category>) {
        val category = categories.data.flatMap { category -> listOf(category.name) }
        val categoryId = categories.data.flatMap { id -> listOf(id.id) }
        setSpiner(spinnerCategory, category, object : DropdownListener {
            override fun getSelected(position: Int) {
                productCategory = categoryId[position]
            }
        })
    }

    private fun productConditionSpinner() {
        val condition = arrayListOf<String>()
        condition.add("Bekas")
        condition.add("Baru")
        setSpiner(spinnerCondition, condition, object : DropdownListener {
            override fun getSelected(position: Int) {
                productCondition = position
            }
        })
    }

    private fun setSpiner(spinner: Spinner, item: List<String>, dropdownListener: DropdownListener) {
        spinner.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, item)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) =
                    dropdownListener.getSelected(position)

        }
    }


    private fun chooseImage() {
        val items = arrayOf<CharSequence>("Take Photo", "Choose from Library", "Cancel")
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.select_action))
        builder.setItems(items, { _, i ->
            when(items[i]) {
                "Take Photo" -> {
                    RxImagePicker.with(activity?.fragmentManager)
                            .requestImage(Sources.CAMERA)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .flatMap({ uri -> RxImageConverters.uriToBitmap(context, uri) })
                            .subscribe({ bitmap -> run {
                                showLoading()
                                setProductImage(bitmap)
                            }
                            })
                }
                "Choose from Library" -> {
                    RxImagePicker.with(activity?.fragmentManager)
                            .requestImage(Sources.GALLERY)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .flatMap({ uri -> RxImageConverters.uriToBitmap(context, uri) })
                            .subscribe({ bitmap -> run {
                                showLoading()
                                setProductImage(bitmap)
                            } })
                }
            }
        })
        builder.show()
    }

    private fun setProductImage(bmp: Bitmap) {
        doAsync {
            val stream = ByteArrayOutputStream()
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()
            productImage = Base64.encodeToString(byteArray, 0)
            uiThread {
                hideLoading()
                Glide.with(context)
                        .load(stream.toByteArray())
                        .asBitmap().centerCrop()
                        .into(imgProduct)
            }
        }
    }

    private fun onCloseDialogClicked() = btnClose.setOnClickListener { dismiss() }

    interface DropdownListener {
        fun getSelected(position: Int)
    }

}
