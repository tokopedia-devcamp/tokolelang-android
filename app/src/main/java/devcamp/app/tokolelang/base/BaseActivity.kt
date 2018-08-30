package devcamp.app.tokolelang.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import devcamp.app.tokolelang.BuildConfig
import devcamp.app.tokolelang.utils.KeyboardUtils
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


/**
 * Created by isfaaghyth on 8/29/18.
 * github: @isfaaghyth
 */
abstract class BaseActivity<P : BasePresenter<*>>: AppCompatActivity(), BaseView {

    protected lateinit var presenter: P
    protected abstract fun initPresenter(): P
    protected abstract fun contentView(): Int
    protected abstract fun onCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView())
        presenter = initPresenter()
        onCreated()
    }

    override fun attachBaseContext(newBase: Context?) =
            super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))

    override fun showLoading() {
        Log.d(BuildConfig.APPLICATION_ID, "Loading")
    }

    override fun hideLoading() {
        Log.d(BuildConfig.APPLICATION_ID, "Complete")
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

    override fun hideKeyboard() = KeyboardUtils.hideSoftInput(this)

    override fun getContext(): Context = applicationContext

}