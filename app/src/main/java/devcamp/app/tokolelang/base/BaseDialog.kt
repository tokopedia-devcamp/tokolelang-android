package devcamp.app.tokolelang.base

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.rahatarmanahmed.cpv.CircularProgressView
import de.mateware.snacky.Snacky
import devcamp.app.tokolelang.BuildConfig

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
abstract class BaseDialog<P: BasePresenter<*>>: BottomSheetDialogFragment(), BaseView {

    protected lateinit var presenter: P
    protected abstract fun initPresenter(): P
    protected abstract fun contentView(): Int
    protected abstract fun onCreated(view: View)
    protected abstract fun loader(): CircularProgressView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(contentView(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = initPresenter()
        onCreated(view)
    }

    override fun showLoading() {
        loader().visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loader().visibility = View.GONE
    }

    override fun onError(message: String?) = Snacky.builder()
            .setActivity(activity)
            .setText(message)
            .setDuration(Snacky.LENGTH_LONG)
            .error()
            .show()

    override fun onError(resId: Int) = onError(getString(resId))

    override fun onInfo(message: String?) = Snacky.builder()
            .setActivity(activity)
            .setText(message)
            .setDuration(Snacky.LENGTH_LONG)
            .info()
            .show()

    override fun onInfo(resId: Int) = onInfo(getString(resId))

    override fun isNetworkConnected(): Boolean? = true
    override fun hideKeyboard() = Unit

}