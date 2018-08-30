package devcamp.app.tokolelang.ui.login

import devcamp.app.tokolelang.base.BaseView
import devcamp.app.tokolelang.data.model.UserRepository

/**
 * Created by isfaaghyth on 8/30/18.
 * github: @isfaaghyth
 */
interface LoginView : BaseView {
    fun onLoginSuccess(result: UserRepository)
}