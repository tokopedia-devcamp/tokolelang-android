package devcamp.app.tokolelang.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.github.rahatarmanahmed.cpv.CircularProgressView
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
    protected abstract fun loader(): CircularProgressView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView())
        presenter = initPresenter()
        onCreated()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun attachBaseContext(newBase: Context?) =
            super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))

    protected fun setNavigationMenu(toolbar: Toolbar?, isHomeDisplay: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(isHomeDisplay)
    }

    protected fun setNavigationUp(toolbar: Toolbar?, isHomeDisplay: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(isHomeDisplay)
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

    override fun hideKeyboard() = KeyboardUtils.hideSoftInput(this)

}