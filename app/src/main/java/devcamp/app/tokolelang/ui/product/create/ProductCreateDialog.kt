package devcamp.app.tokolelang.ui.product.create

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.view.View
import com.github.rahatarmanahmed.cpv.CircularProgressView
import devcamp.app.tokolelang.R
import devcamp.app.tokolelang.base.BaseDialog
import kotlinx.android.synthetic.main.dialog_product_new.*
import android.provider.MediaStore
import android.content.Intent
import android.graphics.Color
import android.view.Gravity
import android.graphics.Color.parseColor
import android.widget.TextView
import android.R.attr.data
import android.support.v4.app.NotificationCompat.getExtras
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import android.graphics.BitmapFactory
import android.R.attr.data
import android.net.Uri
import android.os.AsyncTask
import devcamp.app.tokolelang.R.id.imgAvatar
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v4.graphics.drawable.RoundedBitmapDrawable
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import java.io.ByteArrayOutputStream
import android.support.annotation.NonNull
import com.mlsdev.rximagepicker.RxImageConverters
import io.reactivex.ObservableSource
import com.mlsdev.rximagepicker.Sources
import com.mlsdev.rximagepicker.RxImagePicker
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function3
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

    override fun onCreated(view: View) {
        onCloseDialogClicked()
        imgProduct.setOnClickListener{ chooseImage() }
    }

    private fun chooseImage() {
        val items = arrayOf<CharSequence>("Take Photo", "Choose from Library", "Cancel")
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.select_action))
        builder.setItems(items, { _, i ->
            if (items[i] == "Take Photo") {
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
            } else if (items[i] == "Choose from Library") {
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
        })
        builder.show()
    }

    private fun setProductImage(bmp: Bitmap) {
        doAsync {
            val stream = ByteArrayOutputStream()
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
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

}
