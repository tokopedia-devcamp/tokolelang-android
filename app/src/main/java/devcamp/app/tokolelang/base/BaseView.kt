package devcamp.app.tokolelang.base

import android.content.Context
import android.support.annotation.StringRes

/**
 * Created by isfaaghyth on 8/29/18.
 * github: @isfaaghyth
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(message: String?)
    fun onError(@StringRes resId: Int)
    fun onInfo(message: String?)
    fun onInfo(@StringRes resId: Int)
    fun isNetworkConnected(): Boolean?
    fun hideKeyboard()
}