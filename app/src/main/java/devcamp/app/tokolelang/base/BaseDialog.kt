package devcamp.app.tokolelang.base

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.rahatarmanahmed.cpv.CircularProgressView
import devcamp.app.tokolelang.BuildConfig

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
abstract class BaseDialog<P: BasePresenter<*>>: BottomSheetDialogFragment(), BaseView {

    protected lateinit var presenter: P
    protected abstract fun initPresenter(): P
    protected abstract fun contentView(): Int
    protected abstract fun onCreated()
    protected abstract fun loader(): CircularProgressView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(contentView(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = initPresenter()
        onCreated()
    }

    override fun showLoading() {
        loader().visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loader().visibility = View.GONE
    }

    override fun onError(message: String) {
        Log.d(BuildConfig.APPLICATION_ID, message)
    }

    override fun onError(resId: Int) {
        Log.d(BuildConfig.APPLICATION_ID, getString(resId))
    }

    override fun onInfo(message: String) {
        Log.d(BuildConfig.APPLICATION_ID, message)
    }

    override fun onInfo(resId: Int) {
        Log.d(BuildConfig.APPLICATION_ID, getString(resId))
    }

    override fun isNetworkConnected(): Boolean? = true
    override fun hideKeyboard() = Unit

}